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

package opendct.tuning.upnp;

import opendct.config.Config;
import opendct.config.options.DeviceOption;
import opendct.config.options.DeviceOptionException;
import opendct.nanohttpd.pojo.JsonOption;
import opendct.tuning.discovery.BasicDiscoveredDevice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class UpnpDiscoveredDevice extends BasicDiscoveredDevice {
    private final static Logger logger = LogManager.getLogger(UpnpDiscoveredDevice.class);

    public UpnpDiscoveredDevice(String name, int id, int parentId, String description) {
        super(name, id, parentId, description);
    }

    @Override
    public DeviceOption[] getOptions() {
        try {
            return new DeviceOption[] {
                    getDeviceNameOption()
            };
        } catch (DeviceOptionException e) {
            logger.error("Unable to build options for device => ", e);
        }

        return new DeviceOption[0];
    }

    @Override
    public void setOptions(JsonOption... deviceOptions) throws DeviceOptionException {
        for (JsonOption deviceOption : deviceOptions) {
            if (deviceOption.getProperty().equals(propertiesDeviceName)) {
                setFriendlyName(deviceOption.getValue());
            }

            Config.setJsonOption(deviceOption);
        }
    }
}
