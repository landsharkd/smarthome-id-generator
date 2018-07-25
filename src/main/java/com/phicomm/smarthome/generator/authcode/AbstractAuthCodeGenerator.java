package com.phicomm.smarthome.generator.authcode;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.phicomm.smarthome.cache.Cache;
import com.phicomm.smarthome.generator.enums.ErrorCodeEnum;
import com.phicomm.smarthome.generator.id.SnowFlakeIdGenerator;
import com.phicomm.smarthome.generator.model.request.AbstractMoudleBaseRequest;
import com.phicomm.smarthome.generator.model.request.authcode.AbstractNewAuthCodeRequest;
import com.phicomm.smarthome.generator.model.request.authcode.AbstractVerifyAuthCodeRequest;
import com.phicomm.smarthome.generator.model.result.Result;

/**
 * package: com.phicomm.smarthome.generator.authcode
 * class: AuthCodeGenerator.java
 * date: 2018年6月15日 上午11:49:12
 * author: wen.xia
 * description:
 */
public abstract class AbstractAuthCodeGenerator<T> {
    
    protected Logger log = Logger.getLogger(this.getClass().getSimpleName());
    
    @Autowired
    SnowFlakeIdGenerator snowFlakeIdGenerator;
    
    @Autowired
    Cache redisCache;

    // authCode expiration second : 1800s -> 30 mins
    @Value("${authCode.expirationSeconds}")
    private int expirationSeconds;
    
    public Result<String> newAuthCode(AbstractNewAuthCodeRequest<T> r){
        Result<String> keyResult = generateCacheKey(r);
        if(!keyResult.isSuccess()) {
            return keyResult;
        }
        String code = Objects.toString(snowFlakeIdGenerator.nextId());
        Result<String> cr = putCache(keyResult.getMoudle(), code);
        if(!cr.isSuccess()) {
            return Result.failResult(cr.getErrorCode(), cr.getMsg(), cr.getDesc());
        }
        return Result.successResult(code);
    }
    
    public Result<String> verifyAuthCode(AbstractVerifyAuthCodeRequest<T> r){
        Result<String> keyResult = generateCacheKey(r);
        if(!keyResult.isSuccess()) {
            return keyResult;
        }
        Result<String> cr = getCache(keyResult.getMoudle());
        if(!cr.isSuccess()) {
            return Result.failResult(cr.getErrorCode(), cr.getMsg(), cr.getDesc());
        }
        if(cr.getMoudle() == null || !cr.getMoudle().equals(r.getAuthCode())) {
            return Result.failResult(ErrorCodeEnum.VERIFY_AUTH_CODE_FAILED.getCode(), ErrorCodeEnum.VERIFY_AUTH_CODE_FAILED.getName(), ErrorCodeEnum.VERIFY_AUTH_CODE_FAILED.getDesc());
        }
        return Result.successResult(cr.getMoudle());
    }
    
    public Result<String> invalidAuthCode(AbstractVerifyAuthCodeRequest<T> r){
        Result<String> keyResult = generateCacheKey(r);
        if(!keyResult.isSuccess()) {
            return keyResult;
        }
        Result<String> cr = invalidCache(keyResult.getMoudle());
        if(!cr.isSuccess()) {
            return Result.failResult(cr.getErrorCode(), cr.getMsg(), cr.getDesc());
        }
        return Result.successResult(cr.getMoudle());
    }
    
    protected abstract Result<String> generateCacheKey(AbstractMoudleBaseRequest<T> r);
    
    protected Result<String> putCache(String key, String code){
        try {
            redisCache.put(key, code, expirationSeconds);
        }catch(Exception e) {
            log.error(String.format("redisCache.put error : key=%s, val=%s", key, code), e);
            ErrorCodeEnum ec = ErrorCodeEnum.REDIS_ERROR;
            return Result.failResult(ec.getCode(), ec.getName(), e.getMessage());
        }
        
        return Result.successResult(code);
    }
    
    protected Result<String> getCache(String key){
        try {
            return Result.successResult((String)redisCache.get(key));
        }catch(Exception e) {
            log.error(String.format("redisCache.get error : key=%s", key), e);
            ErrorCodeEnum ec = ErrorCodeEnum.REDIS_ERROR;
            return Result.failResult(ec.getCode(), ec.getName(), e.getMessage());
        }
    }
    
    protected Result<String> invalidCache(String key){
        try {
            redisCache.delete(key);
        }catch(Exception e) {
            log.error(String.format("redisCache.delete error : key=%s", key), e);
            ErrorCodeEnum ec = ErrorCodeEnum.REDIS_ERROR;
            return Result.failResult(ec.getCode(), ec.getName(), e.getMessage());
        }
        return Result.successResult(null);
    }
}
