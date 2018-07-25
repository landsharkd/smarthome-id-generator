package com.phicomm.smarthome.generator.model.request.authcode;

import com.phicomm.smarthome.generator.model.request.AbstractMoudleBaseRequest;

/**
 * package: com.phicomm.smarthome.generator.model.request
 * class: AbstractNewTokensRequest.java
 * date: 2018年6月15日 下午2:31:22
 * author: wen.xia
 * description:
 */
public abstract class AbstractVerifyAuthCodeRequest<T> extends AbstractMoudleBaseRequest<T>{

    private static final long serialVersionUID = -4222738647673265386L;
    
    private String authCode;

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    } 
 
}
