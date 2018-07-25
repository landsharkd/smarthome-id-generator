package com.phicomm.smarthome.generator.authcode;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.phicomm.smarthome.generator.enums.ErrorCodeEnum;
import com.phicomm.smarthome.generator.model.Device;
import com.phicomm.smarthome.generator.model.request.AbstractMoudleBaseRequest;
import com.phicomm.smarthome.generator.model.result.Result;

/**
 * package: com.phicomm.smarthome.generator.authcode
 * class: AuthCodeGenerator.java
 * date: 2018年6月19日 上午11:16:15
 * author: wen.xia
 * description:
 */
@Component
public class DeviceAuthCodeGenerator extends AbstractAuthCodeGenerator<Device>{
    
    private String sep = "-";
    
    private String prefix = this.getClass().getSimpleName();
    
    private String postfix = "deviceId";
    
    @Override
    protected Result<String> generateCacheKey(AbstractMoudleBaseRequest<Device> r) {
        if(r != null && r.getMoudle() != null && StringUtils.isNotBlank(r.getMoudle().getDeviceId())) {
             String key = prefix + sep +  r.getMoudle().getDeviceId() + sep + postfix;
             return Result.successResult(key);
        }
        return Result.failResult(ErrorCodeEnum.INVALID_PARAMS.getCode(), ErrorCodeEnum.INVALID_PARAMS.getName(), "deviceId is required");
    }

}
