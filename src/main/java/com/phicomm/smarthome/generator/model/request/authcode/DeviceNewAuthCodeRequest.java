package com.phicomm.smarthome.generator.model.request.authcode;

import com.phicomm.smarthome.generator.model.Device;
import com.phicomm.smarthome.generator.model.IDevice;

/**
 * package: com.phicomm.smarthome.generator.model.request.authcode
 * class: DeviceNewAuthCodeRequest.java
 * date: 2018年6月19日 上午11:09:39
 * author: wen.xia
 * description:
 */
public class DeviceNewAuthCodeRequest extends AbstractNewAuthCodeRequest<Device> implements IDevice{

    private static final long serialVersionUID = -7458288554367859024L;

    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

}
