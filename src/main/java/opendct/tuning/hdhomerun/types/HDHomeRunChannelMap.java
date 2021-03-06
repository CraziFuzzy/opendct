/*
 * Copyright 2016 The OpenDCT Authors. All Rights Reserved
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

package opendct.tuning.hdhomerun.types;

public enum HDHomeRunChannelMap {
    // Page 4: https://www.silicondust.com/hdhomerun/hdhomerun_development.pdf

    UNKNOWN("unknown", "Unknown"),
    US_BCAST("us-bcast", "US, Canada"),
    US_CABLE("us-cable", "US, Canada"),
    US_HRC("us-hrc", "US, Canada"),
    US_IRC("us-irc", "US, Canada"),
    EU_BCAST("eu-bcast", "Europe, New Zealand"),
    EU_CABLE("eu-cable", "Europe, New Zealand");

    public final String CHANNEL_MAP_NAME;
    public final String LOCATION;

    HDHomeRunChannelMap(String channelMapName, String location) {
        CHANNEL_MAP_NAME = channelMapName;
        LOCATION = location;
    }
}
