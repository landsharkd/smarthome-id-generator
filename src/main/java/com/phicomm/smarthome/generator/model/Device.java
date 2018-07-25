package com.phicomm.smarthome.generator.model;

/**
 * package: com.phicomm.smarthome.generator.model
 * class: Device.java
 * date: 2018年6月15日 下午2:42:45
 * author: wen.xia
 * description:
 */
public class Device extends BaseDO implements IDevice{

    private static final long serialVersionUID = -2522296190972914047L;
    
    private String deviceId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    
}
