package com.phicomm.smarthome.generator.model;

/**
 * package: com.phicomm.smarthome.generator.model
 * class: TokenWraper.java
 * date: 2018年6月14日 下午4:37:43
 * author: wen.xia
 * description:
 */
public class TokenWraper extends BaseDO{

    private static final long serialVersionUID = 5852267698623433550L;
    //token字符串
    private String token;
    //过期时间
    private long exp;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public long getExp() {
        return exp;
    }
    public void setExp(long exp) {
        this.exp = exp;
    }
    
    
}
