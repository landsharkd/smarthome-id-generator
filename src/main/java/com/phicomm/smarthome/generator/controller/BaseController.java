package com.phicomm.smarthome.generator.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.phicomm.smarthome.generator.enums.ErrorCodeEnum;
import com.phicomm.smarthome.generator.model.request.AbstractBaseRequest;
import com.phicomm.smarthome.generator.model.result.Result;
import com.phicomm.smarthome.generator.processor.IBaseProcessor;

/**
 * package: com.phicomm.smarthome.generator.controller
 * class: BaseController.java
 * date: 2018年6月15日 下午4:00:03
 * author: wen.xia
 * description:
 */
public abstract class BaseController {
    
    protected Logger log = Logger.getLogger(this.getClass().getSimpleName());
/*    
    //接口调用的accessKey
    @Value("${id.generator.accessKey}")
    private String accessKey = "access123";*/
    
    //生产ids时，是否需要提供调用者来源
    @Value("${id.generator.isRequiredSource}")
    private boolean isRequiredSource = true;
    
    @Autowired
    protected IBaseProcessor processor;
    
    protected Result<Object> validCommonParams(HttpServletRequest request, AbstractBaseRequest abr){
        String sourceAddr = getIpAdrress(request);
        if(StringUtils.isNotBlank(sourceAddr)) {
            abr.setSourceAddr(request.getRemoteAddr());
        }
        Result<Object> result = Result.successResult(null);
        if(this.isRequiredSource() && StringUtils.isBlank(abr.getSource())) {
            result = Result.failResult(ErrorCodeEnum.INVALID_PARAMS.getCode(), ErrorCodeEnum.INVALID_PARAMS.getDesc(), "source is required", null);
            return result;
        }
        
        /*if(StringUtils.isNotBlank(this.getAccessKey()) && !this.getAccessKey().equals(abr.getAccessKey())) {
            result = Result.failResult(ErrorCodeEnum.INVALID_PARAMS.getCode(), ErrorCodeEnum.INVALID_PARAMS.getDesc(), "accessKey is not correct", null);
            return result;
        }*/
        return Result.successResult(null);
    }
    

    private String getIpAdrress(HttpServletRequest request) {
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if(index != -1){
                return XFor.substring(0,index);
            }else{
                return XFor;
            }
        }
        XFor = Xip;
        if(StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)){
            return XFor;
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }

    public boolean isRequiredSource() {
        return isRequiredSource;
    }
    
    protected <T>Object calFinalResult(Result<T> r){
        if(r.getConvertMoudle() != null) {
            if(r.getDebugInfo() != null) {
                List<Object> list = new ArrayList<>();
                list.add(r.getDebugInfo());
                list.add(r.getConvertMoudle());
                return list;
            }
            return r.getConvertMoudle();
        }
        return r;
    }
    
    
}
