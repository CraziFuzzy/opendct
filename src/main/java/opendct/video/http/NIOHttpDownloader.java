/*
 * Copyright 2015 The OpenDCT Authors. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package opendct.video.http;

import opendct.config.Config;
import opendct.producer.HTTPProducerImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOHttpDownloader {
    private final static Logger logger = LogManager.getLogger(HTTPProducerImpl.class);

    // Carriage return and line feed are required regardless of the OS.
    private static final String NEW_LINE = "\r\n";
    private static final String GET_HEAD = "GET ";
    private static final String GET_TAIL = " HTTP/1.1" + NEW_LINE;
    private static final String HOST_HEAD = "Host: ";
    private static final String CONNECTION =
            "Connection: keep-alive" + NEW_LINE +
            "Accept: video/mpeg" + NEW_LINE +
            "User-Agent: OpenDCT" + NEW_LINE +
                    NEW_LINE;

    private boolean closed = false;
    private final SocketChannel socketChannel;
    private URL address;
    private ByteBuffer tempBuffer;

    private String mimeType;

    public NIOHttpDownloader() throws IOException {
        socketChannel = SocketChannel.open();
    }

    /**
     * Connect to the provided address and start content download.
     *
     * @param address The URL to download.
     * @throws IOException Thrown if the connection cannot be established or the requested file does
     *                     not exist.
     */
    public void connect(URL address) throws IOException {
        if (closed) {
            return;
        }
        tempBuffer = ByteBuffer.allocateDirect(1024);

        int port = address.getPort();

        if (port < 1) {
            port = 80;
        }

        socketChannel.connect(new InetSocketAddress(address.getHost(), port));

        tempBuffer.clear();
        tempBuffer.put((
                GET_HEAD + address.getFile() + GET_TAIL +
                        HOST_HEAD + address.getAuthority() + NEW_LINE +
                        CONNECTION
        ).getBytes(Config.STD_BYTE));

        tempBuffer.flip();
        while (tempBuffer.hasRemaining()) {
            socketChannel.write(tempBuffer);
        }

        StringBuilder stringBuffer = new StringBuilder(1024);
        StringBuilder logBuilder = new StringBuilder(1024);

        boolean success = false;
        boolean startStreaming = false;
        char currentByte;
        int contentLength = 0;

        while (!startStreaming) {
            tempBuffer.clear();
            socketChannel.read(tempBuffer);
            tempBuffer.flip();

            while (tempBuffer.hasRemaining()) {
                currentByte = (char) tempBuffer.get();

                if (currentByte == '\n') {
                    if (stringBuffer.length() == 0) {
                        startStreaming = true;
                    }

                    String line = stringBuffer.toString();
                    logBuilder.append("'").append(stringBuffer).append("', ");

                    if (!success && line.startsWith("HTTP/1.1")) {
                        if (!line.endsWith(" 200 OK")) {
                            logger.error("HTTP Error: {}", logBuilder);
                            throw new IOException("Server responded " + line);
                        } else {
                            success = true;
                        }
                    } else if (line.startsWith("Content-Type: ")) {
                        mimeType = line.substring("Content-Type: ".length());
                    } else if (line.startsWith("Content-Length: ")) {
                        try {
                            contentLength = Integer.parseInt(
                                    line.substring("Content-Length: ".length()));
                        } catch (NumberFormatException e) {
                            logger.warn("Unable to parse content length from '{}' => ",
                                    line, e.getMessage());
                        }
                    }

                    stringBuffer.setLength(0);
                    continue;
                } else if (currentByte == '\r') {
                    continue;
                }

                stringBuffer.append(currentByte);
            }
        }

        logger.debug("HTTP Response: {}", logBuilder);
    }

    /**
     * Read from the HTTP connection into the provided ByteBuffer.
     * <p/>
     * This method is designed for massive inbound mpeg/video stream transferring, not general HTTP
     * communications.
     *
     * @param buffer The buffer to read into. The buffer must have at least 1024 bytes available.
     * @return The number of bytes read. -1 if the stream is closed.
     * @throws IOException Thrown if an I/O error occurs.
     */
    public int read(ByteBuffer buffer) throws IOException {
        if (closed) {
            return -1;
        }

        // I have yet to see any actual data remaining in the temporary buffer, but it should be
        // faster to return that it's null vs. executing a method with every pass.
        if (tempBuffer != null && tempBuffer.hasRemaining()) {
            int remaining = tempBuffer.remaining();

            while (tempBuffer.hasRemaining()) {
                buffer.put(tempBuffer);
            }
            tempBuffer = null;

            return remaining + socketChannel.read(buffer);
        }

        return socketChannel.read(buffer);
    }

    public void close() {
        closed = true;

        try {
            socketChannel.close();
            socketChannel.socket().close();
        } catch (IOException e) {
            logger.debug("An exception was created when the socket channel was close => ", e);
        }
    }

    public boolean isOpen() {
        return !closed;
    }
}
