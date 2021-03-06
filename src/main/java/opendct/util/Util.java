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

package opendct.util;

import opendct.config.Config;
import opendct.consumer.upload.NIOSageTVMediaServer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Enumeration;

public class Util {

    /**
     * Get a local IP address with a from a remote IP address that are on the same subnet.
     *
     * @param remoteAddress This is the address of the remote device.
     * @return The first local address that is on the same subnet as the remote device or null if no
     * IP address was found.
     * @throws SocketException Thrown if IO error happens.
     */
    public static InetAddress getLocalIPForRemoteIP(InetAddress remoteAddress) throws SocketException {
        byte remoteAddressBytes[] = remoteAddress.getAddress();

        Enumeration<NetworkInterface> networkInterface = NetworkInterface.getNetworkInterfaces();

        while (networkInterface.hasMoreElements()) {
            NetworkInterface currentInterface = networkInterface.nextElement();

            // Never try to match a loopback interface.
            if (currentInterface.isLoopback()) {
                continue;
            }

            for (InterfaceAddress address : currentInterface.getInterfaceAddresses()) {
                byte localIPAddress[] = address.getAddress().getAddress();

                // Make sure these are the same kind of IP addresses. This should only differ if one of
                // the addresses is v4 and the other is v6.
                if (localIPAddress.length != remoteAddressBytes.length) {
                    continue;
                }

                byte localSubnet[] = new byte[localIPAddress.length];
                int localSubnetLength = address.getNetworkPrefixLength();

                boolean match = true;
                int bits = localSubnetLength;
                for (int i = 0; i < localSubnet.length; i++) {
                    if (bits < 8) {
                        localSubnet[i] = (byte) ~((1 << 8 - bits) - 1);

                        byte localAnd = (byte) (localIPAddress[i] & localSubnet[i]);
                        byte remoteAnd = (byte) (remoteAddressBytes[i] & localSubnet[i]);

                        if (localAnd != remoteAnd) {
                            match = false;
                            break;
                        }
                    } else if (!(localIPAddress[i] == remoteAddressBytes[i])) {
                        match = false;
                        break;
                    }

                    bits -= 8;
                }

                if (!match) {
                    continue;
                }

                return address.getAddress();
            }
        }

        return null;
    }

    public static NetworkInterface getNetworkInterfaceForRemoteIPAddress(InetAddress remoteAddress) throws SocketException {
        byte remoteAddressBytes[] = remoteAddress.getAddress();

        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface currentInterface = networkInterfaces.nextElement();

            // Never try to match a loopback interface.
            if (currentInterface.isLoopback()) {
                continue;
            }

            for (InterfaceAddress address : currentInterface.getInterfaceAddresses()) {
                byte localIPAddress[] = address.getAddress().getAddress();

                // Make sure these are the same kind of IP addresses. This should only differ if one of
                // the addresses is v4 and the other is v6.
                if (localIPAddress.length != remoteAddressBytes.length) {
                    continue;
                }

                byte localSubnet[] = new byte[localIPAddress.length];
                int localSubnetLength = address.getNetworkPrefixLength();

                boolean match = true;
                int bits = localSubnetLength;
                for (int i = 0; i < localSubnet.length; i++) {
                    if (bits < 8) {
                        localSubnet[i] = (byte) ~((1 << 8 - bits) - 1);

                        byte localAnd = (byte) (localIPAddress[i] & localSubnet[i]);
                        byte remoteAnd = (byte) (remoteAddressBytes[i] & localSubnet[i]);

                        if (localAnd != remoteAnd) {
                            match = false;
                            break;
                        }
                    } else if (!(localIPAddress[i] == remoteAddressBytes[i])) {
                        match = false;
                        break;
                    }

                    bits -= 8;
                }

                if (!match) {
                    continue;
                }

                return currentInterface;
            }
        }

        return null;
    }

    public static String[] arrayToStringArray(boolean[] values) {
        String returnValues[] = new String[values.length];

        for (int i = 0; i < values.length; i++) {
            returnValues[i] = String.valueOf(values[i]);
        }

        return returnValues;
    }

    public static String[] arrayToStringArray(int[] values) {
        String returnValues[] = new String[values.length];

        for (int i = 0; i < values.length; i++) {
            returnValues[i] = String.valueOf(values[i]);
        }

        return returnValues;
    }

    public static String[] arrayToStringArray(long[] values) {
        String returnValues[] = new String[values.length];

        for (int i = 0; i < values.length; i++) {
            returnValues[i] = String.valueOf(values[i]);
        }

        return returnValues;
    }

    public static String[] arrayToStringArray(float[] values) {
        String returnValues[] = new String[values.length];

        for (int i = 0; i < values.length; i++) {
            returnValues[i] = String.valueOf(values[i]);
        }

        return returnValues;
    }

    public static String[] arrayToStringArray(double[] values) {
        String returnValues[] = new String[values.length];

        for (int i = 0; i < values.length; i++) {
            returnValues[i] = String.valueOf(values[i]);
        }

        return returnValues;
    }

    public static String[] getStringArrayFromCSV(String stringValue) {
        String returnValue[] = new String[0];

        if (stringValue != null) {
            if (!stringValue.equals("")) {
                // The parsing regex will tolerate white space between commas.
                returnValue = stringValue.split("\\s*,\\s*");
            }
        }

        return returnValue;
    }

    public static boolean createDirectory(String directory) {
        File file = new File(directory);

        boolean returnValue = file.exists();

        if (!returnValue) {
            try {
                returnValue = file.mkdirs();
            } catch (Exception e) {
                returnValue = false;
            }
        }

        return returnValue;
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.length() == 0;
    }

    /**
     * Appends to the end of a file opened via the SageTV media server.
     *
     * @param source The source file.
     * @param mediaServer The connected media server.
     * @throws IOException Thrown if there is a problem copying or if the media server is not
     *                     connected.
     */
    public static void appendFile(File source, NIOSageTVMediaServer mediaServer) throws IOException {
        FileChannel inputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            ByteBuffer buffer = ByteBuffer.allocateDirect(1048576);
            while (true) {
                buffer.clear();
                int read = inputChannel.read(buffer);
                if (read == -1) {
                    break;
                }
                buffer.flip();
                mediaServer.uploadAutoIncrement(buffer);
            }
        } finally {
            if (inputChannel != null && inputChannel.isOpen()) {
                inputChannel.close();
            }
        }
    }

    /**
     * Copies a file.
     *
     * @param source The source file.
     * @param destination The destination file.
     * @param overwrite <i>true</i> to delete the file if it already exists.
     * @throws IOException Thrown if there is a problem copying or if the file already exists and
     *                     <b>overwrite</b> is <i>false</i>.
     */
    public static void copyFile(File source, File destination, boolean overwrite) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;

        if(destination.exists()) {
            if (overwrite) {
                if (!destination.delete()) {
                    throw new IOException("Unable to delete the destination file.");
                }
            } else {
                throw new IOException("The file already exists.");
            }
        }

        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(destination).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            if (inputChannel != null && inputChannel.isOpen()) {
                inputChannel.close();
            }
            if (outputChannel != null && outputChannel.isOpen()) {
                outputChannel.close();
            }
        }
    }

    /**
     * Appends to the end of a file.
     *
     * @param source The source file.
     * @param destination The destination file.
     * @throws IOException Thrown if there is a problem copying or if the file already exists and
     *                     <b>overwrite</b> is <i>false</i>.
     */
    public static void appendFile(File source, File destination) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;

        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = FileChannel.open(
                    Paths.get(destination.getAbsolutePath()),
                    StandardOpenOption.APPEND);

            outputChannel.position(outputChannel.size());
            outputChannel.transferFrom(inputChannel, outputChannel.size(), inputChannel.size());
        } finally {
            if (inputChannel != null && inputChannel.isOpen()) {
                inputChannel.close();
            }
            if (outputChannel != null && outputChannel.isOpen()) {
                outputChannel.close();
            }
        }
    }

    /**
     * Returns a filename that did not exist based on the desired filename.
     * <p/>
     * This is done by appending a number to the end of the filename until the file doesn't exist.
     * This method is thread-safe in that it will not allow another thread to be told it can use the
     * same file.
     *
     * @param path This is the directory the file is to be created in.
     * @param prepend This is the start of the filename we want to based the new filename on.
     * @param append This is the end of the filename we want to based the new filename on.
     * @return A filename that has been touched.
     */
    public synchronized static File getFileNotExist(String path, String prepend, String append) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            File newFile = new File(path + Config.DIR_SEPARATOR + prepend + i + append);

            if (!newFile.exists()) {
                try {
                    newFile.createNewFile();
                    return newFile;
                } catch (IOException e) {}
            }
        }

        return null;
    }

    /**
     * Remove every element that matches a string from an array.
     *
     * @param remove The element to be removed.
     * @param array The String array to remove the element from.
     * @return A new array.
     */
    public static String[] removeFromArray(String remove, String array[]) {
        ArrayList<String> returnValues = new ArrayList<>();

        for (String value : array) {
            if (!value.equals(remove)) {
                returnValues.add(value);
            }
        }

        return returnValues.toArray(new String[returnValues.size()]);
    }

    /**
     * Connects to a URL and returns an XML DOM parser.
     *
     * @param address This is the URL to download.
     * @param username This is an optional username for the connection.
     * @param password This is an optional password for the connection.
     * @param timeout This is a timeout for the connection. 0 = infinite
     * @return An parsed XML document or null if the document could not be parsed.
     * @throws IOException If the connection could not be be established or there was a problem
     *                     reading the returned data.
     */
    private static DocumentBuilderFactory domFactory;
    private static DocumentBuilder domBuilder;
    public static Document getUrlXml(URL address, final String username, final String password, int timeout) throws IOException {
        String returnValue[];

        final URLConnection connection = address.openConnection();
        connection.setConnectTimeout(timeout);

        if (!Util.isNullOrEmpty(username) && !Util.isNullOrEmpty(password)) {
            String encodedBytes = DatatypeConverter.printBase64Binary((username + ":" + password).getBytes(StandardCharsets.UTF_8));
            connection.setRequestProperty("Authorization", "Basic " + encodedBytes);
        }

        if (domFactory == null) {
            domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setValidating(true);
            domFactory.setIgnoringElementContentWhitespace(true);

            try {
                domBuilder = domFactory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                domFactory = null;
                return null;
            }
        }

        try {
            return domBuilder.parse(connection.getInputStream());
        } catch (SAXException e) {
            throw new IOException(e);
        }
    }

    public static Node getDeepNode(String name[], NodeList nodes) {
        for (int i = 0; i < nodes.getLength(); i++ ) {
            Node node = nodes.item(i);
            if (node.getNodeName().equalsIgnoreCase(name[0])) {
                if (name.length == 1) {
                    return node;
                } else {
                    return getDeepNode(name, node.getChildNodes(), 1);
                }
            }
        }

        return null;
    }

    public static Node getDeepNode(String name[], NodeList nodes, int offset) {
        for (int i = 0; i < nodes.getLength(); i++ ) {
            Node node = nodes.item(i);
            if (node.getNodeName().equalsIgnoreCase(name[offset])) {
                if (name.length - 1 == offset) {
                    return node;
                } else {
                    return getDeepNode(name, node.getChildNodes(), offset + 1);
                }
            }
        }

        return null;
    }

    public Node getNode(String name, NodeList nodes) {
        for (int i = 0; i < nodes.getLength(); i++ ) {
            Node node = nodes.item(i);
            if (node.getNodeName().equalsIgnoreCase(name)) {
                return node;
            }
        }

        return null;
    }
}
