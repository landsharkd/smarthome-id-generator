package com.phicomm.smarthome.generator.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phicomm.smarthome.cache.Cache;
import com.phicomm.smarthome.generator.authcode.DeviceAuthCodeGenerator;
import com.phicomm.smarthome.generator.model.Device;
import com.phicomm.smarthome.generator.model.IDevice;
import com.phicomm.smarthome.generator.model.request.AbstractMoudleBaseRequest;
import com.phicomm.smarthome.generator.model.request.authcode.DeviceNewAuthCodeRequest;
import com.phicomm.smarthome.generator.model.request.authcode.DeviceVerifyAuthCodeRequest;
import com.phicomm.smarthome.generator.model.result.Result;

/**
 * tController
 * package: com.phicomm.smarthome.phihome.controller.skill
 * class: SkillController.java
 * date: 2018年6月12日 上午11:18:41
 * author: wen.xia
 * description:
 */
@RestController
public class AuthCodeController extends BaseController{
    
    
    @Autowired
    DeviceAuthCodeGenerator deviceAuthCodeGenerator;

    @RequestMapping(value = "/newAuthCode", method = RequestMethod.GET, produces = {"application/json"})
    public Object newAuthCode(HttpServletRequest request, @NotNull /*@RequestBody*/ DeviceNewAuthCodeRequest dnacr) {
        processor.processPre(dnacr);
        Result<Object> r = newAuthCode0(request, dnacr);
        processor.processPost(r, dnacr);
        return calFinalResult(r);
    }
    
    public Result<Object> newAuthCode0(HttpServletRequest request, @NotNull /*@RequestBody*/ DeviceNewAuthCodeRequest dnacr) {
        fillDeviceMoudle(dnacr, dnacr);
        Result<Object> vResult = validCommonParams(request, dnacr);
        if(!vResult.isSuccess()) {
            return Result.failResult(vResult.getErrorCode(), vResult.getMsg(), vResult.getDesc());
        }
        Result<String> r = deviceAuthCodeGenerator.newAuthCode(dnacr);
        if(!r.isSuccess()) {
            return Result.failResult(r.getErrorCode(), r.getMsg(), r.getDesc());
        }
        Map<String, String> rMap = new HashMap<>();
        rMap.put("code", r.getMoudle());
        return Result.successResult(rMap);
    }
    
    @RequestMapping(value = "/verifyAuthCode", method = RequestMethod.GET, produces = {"application/json"})
    public Object verifyAuthCode(HttpServletRequest request, @NotNull /*@RequestBody*/ DeviceVerifyAuthCodeRequest dvacr) {
        processor.processPre(dvacr);
        Result<Object> r = verifyAuthCode0(request, dvacr);
        processor.processPost(r, dvacr);
        return calFinalResult(r);
    }
    
    public Result<Object> verifyAuthCode0(HttpServletRequest request, @NotNull /*@RequestBody*/ DeviceVerifyAuthCodeRequest dvacr) {
        fillDeviceMoudle(dvacr, dvacr);
        Result<Object> vResult = validCommonParams(request, dvacr);
        if(!vResult.isSuccess()) {
            return Result.failResult(vResult.getErrorCode(), vResult.getMsg(), vResult.getDesc());
        }
        Result<String> r = deviceAuthCodeGenerator.verifyAuthCode(dvacr);
        if(!r.isSuccess()) {
            return Result.failResult(r.getErrorCode(), r.getMsg(), r.getDesc());
        }
        Map<String, String> rMap = new HashMap<>();
        rMap.put("code", r.getMoudle());
        return Result.successResult(rMap);
    }
    
    private void fillDeviceMoudle(AbstractMoudleBaseRequest<Device> r, IDevice d) {
        r.setMoudle(buildDevice(d));
    }
    
    private Device buildDevice(IDevice device) {
        Device d = new Device();
        d.setDeviceId(device.getDeviceId());
        return d;
    }
    
    

}
