package com.phicomm.smarthome.generator.model.request.authcode;

import com.phicomm.smarthome.generator.model.Device;
import com.phicomm.smarthome.generator.model.IDevice;

/**
 * package: com.phicomm.smarthome.generator.model.request.authcode
 * class: DeviceVerifyAuthCodeRequest.java
 * date: 2018年6月19日 上午11:08:52
 * author: wen.xia
 * description:
 */
public class DeviceVerifyAuthCodeRequest extends AbstractVerifyAuthCodeRequest<Device> implements IDevice{

    private static final long serialVersionUID = 3171309431380581376L;
    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
