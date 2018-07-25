package com.phicomm.smarthome.generator.model.result;

import java.util.Objects;

import org.apache.commons.lang.StringUtils;

import com.phicomm.smarthome.generator.enums.ErrorCodeEnum;
import com.phicomm.smarthome.generator.model.BaseDO;
import com.phicomm.smarthome.generator.model.DebugInfo;

/**
 * package: com.phicomm.smarthome.phihome.protocol.skill
 * class: Result.java
 * date: 2018年6月6日 上午9:37:27
 * author: wen.xia
 * description:
 */
public class Result<T> extends BaseDO{

    private static final long serialVersionUID = -3633579449096095816L;
    
    private boolean isSuccess;
    private int errorCode;
    private String code;
    private String msg;
    private String desc;
    private T moudle;
    private DebugInfo debugInfo;
    private Object convertMoudle;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getMoudle() {
        return moudle;
    }

    public void setMoudle(T moudle) {
        this.moudle = moudle;
    }

    public Result(boolean isSuccess, int errorCode, String code, String msg, String desc, T moudle) {
        this.isSuccess = isSuccess;
        this.errorCode = errorCode;
        this.msg = msg;
        this.moudle = moudle;
        this.desc = desc;
        this.code = code;
    }

    public static <T> Result<T> successResult(T t) {
        return new Result<>(true, ErrorCodeEnum.SUCCESS.getCode(), Objects.toString(ErrorCodeEnum.SUCCESS.getCode()), ErrorCodeEnum.SUCCESS.getName(), ErrorCodeEnum.SUCCESS.getDesc(), t);
    }

    public static <T>Result<T> failResult(int code, String msg, String desc) {
        return new Result<>(false, code, String.valueOf(code), msg, desc, null);
    }
    
    public static <T>Result<T> failResult(int code, String msg, String desc, T t) {
        return new Result<>(false, code, String.valueOf(code), msg, desc, t);
    }
    
    public static <T>Result<T> failResult(String code, String msg, T t) {
        Result<T> r = new Result<>(false, -1, code, msg, msg, t);
        if(StringUtils.isNumeric(code)) {
            try {
                int c = Integer.parseInt(code);
                r.setErrorCode(c);
            }catch(Exception e) {
                
            }
        }
        return r;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DebugInfo getDebugInfo() {
        return debugInfo;
    }

    public void setDebugInfo(DebugInfo debugInfo) {
        this.debugInfo = debugInfo;
    }

    public Object getConvertMoudle() {
        return convertMoudle;
    }

    public void setConvertMoudle(Object convertMoudle) {
        this.convertMoudle = convertMoudle;
    }
    
    
    
}
