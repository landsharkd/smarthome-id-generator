package com.phicomm.smarthome.generator.enums;

import com.phicomm.smarthome.consts.PhihomeConst;

/**
 * package: com.phicomm.smarthome.phihome.protocol.skill
 * class: DeviceTypeEnum.java
 * date: 2018年6月6日 下午3:26:48
 * author: wen.xia
 * description:
 */
public enum ErrorCodeEnum {
    
    SUCCESS(PhihomeConst.ResponseStatus.STATUS_OK, "success", "成功"),
    INVALID_PARAMS(PhihomeConst.ResponseStatus.STATUS_INVAID_PARA, "invalid params", "请求参数不合法"),
    
    VERIFY_AUTH_CODE_FAILED(14009, "verify authCode failed", "验证码校验失败"),
    TOKEN_ILLEGAL(14010, "token illegal", "token非法"),
    TOKEN_EXPIRED(14011, "token expired", "token过期"),
    TOKEN_DESTROYED(14012, "token destroyed ", "token已注销"),
    TOKEN_REFRESHED(14013, "token refreshed", "token已刷新"),
    TOKEN_NOT_MATCH_CLAIMS(14014, "token not match id", "token与claims不匹配"),
    
    REFRESH_TOKEN_ILLEGAL(14020, "refreshToken illegal", "refreshToken非法"),
    REFRESH_TOKEN_EXPIRED(14021, "refreshToken expired", "refreshToken过期"),
    REFRESH_TOKEN_DESTROYED(14022, "refreshToken destroyed ", "refreshToken已注销"),
    REFRESH_TOKEN_REFRESHED(14023, "refreshToken refreshed", "refreshToken已刷新"),
    REFRESH_TOKEN_NOT_MATCH_CLAIMS(14024, "refreshToken not match id", "refreshToken与claims不匹配"),
    
    REDIS_ERROR(14030, "redis error", "redis error"),
    
    SYS_ERROR(PhihomeConst.ResponseStatus.STATUS_COMMON_FAILED, "system error", "请求失败"),
    UNKONWN(102, "unknown reason", "未知错误")
    
    ;
    
    private int code;
    private String name;
    private String desc;
    private ErrorCodeEnum(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc  = desc;
    }
    public int getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public String getDesc() {
        return desc;
    }
    
    public static ErrorCodeEnum getByName(String name) {
        for(ErrorCodeEnum dte : ErrorCodeEnum.values()) {
            if(dte.getName().equals(name)) {
                return dte;
            }
        }
        return UNKONWN;
    }
    
    public static ErrorCodeEnum getByCode(int code) {
        for(ErrorCodeEnum dte : ErrorCodeEnum.values()) {
            if(dte.getCode() == code) {
                return dte;
            }
        }
        return UNKONWN;
    }
    
}
