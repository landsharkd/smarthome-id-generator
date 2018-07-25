package com.phicomm.smarthome.generator.model.request;

import com.phicomm.smarthome.generator.model.BaseDO;
import com.phicomm.smarthome.generator.model.DebugInfo;
import com.phicomm.smarthome.generator.model.request.IRequest;

/**
 * package: com.phicomm.r1mini.request
 * class: IdsGeneratorRequest.java
 * date: 2018年6月12日 下午4:04:47
 * author: wen.xia
 * description:
 */
public abstract class AbstractBaseRequest extends BaseDO implements IRequest{

    private static final long serialVersionUID = -8125588859252415121L;

    //调用者来源，可以为app name, 必填
    private String source;
   
    //调用者ip
    private String sourceAddr;
    
    //接入密码
    private String accessKey;
    
    //是否是调试请求
    private boolean debug;
    
    //调试信息
    private DebugInfo debugInfo;
    
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceAddr() {
        return sourceAddr;
    }

    public void setSourceAddr(String sourceAddr) {
        this.sourceAddr = sourceAddr;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public DebugInfo getDebugInfo() {
        return debugInfo;
    }

    public void setDebugInfo(DebugInfo debugInfo) {
        this.debugInfo = debugInfo;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

}
