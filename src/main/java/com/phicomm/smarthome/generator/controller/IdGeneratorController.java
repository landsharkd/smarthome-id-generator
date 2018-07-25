package com.phicomm.smarthome.generator.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phicomm.smarthome.generator.enums.ErrorCodeEnum;
import com.phicomm.smarthome.generator.id.SnowFlakeIdGenerator;
import com.phicomm.smarthome.generator.model.request.id.IdsGeneratorRequest;
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
public class IdGeneratorController extends BaseController{
    
    
    @Autowired
    SnowFlakeIdGenerator snowFlakeIdGenerator;
    
    //生产360个id大约需要2ms, 则1000ms可以处理500个id生产请求.
    @Value("${id.generator.maxIdsSize}")
    private int maxIdsSize = 350;

    @RequestMapping(value = "/idsGenerator", method = RequestMethod.GET, produces = {"application/json"})
    public Object generate(HttpServletRequest request, /*@RequestBody*/ IdsGeneratorRequest requestModel) {
        processor.processPre(requestModel);
        Result<Map<String, Object>> r = generate0(request, requestModel);
        processor.processPost(r, requestModel);
        return calFinalResult(r);
    }
    public Result<Map<String, Object>> generate0(HttpServletRequest request, IdsGeneratorRequest requestModel) {
        if(requestModel == null) {
            requestModel = new IdsGeneratorRequest();
        }
        
        Result<Object> vResult = validCommonParams(request, requestModel);
        if(!vResult.isSuccess()) {
            return Result.failResult(vResult.getErrorCode(), vResult.getMsg(), vResult.getDesc());
        }
        
        Result<Map<String, Object>> result = Result.successResult(null);
        if(requestModel.getSize() <= 0) {
            result = Result.failResult(ErrorCodeEnum.INVALID_PARAMS.getCode(), ErrorCodeEnum.INVALID_PARAMS.getDesc(), "size should be in 1 ~ " + this.getMaxIdsSize(), null);
            return result;
        }
        
        if(this.getMaxIdsSize() > 0 && requestModel.getSize() > this.getMaxIdsSize()) {
            result = Result.failResult(ErrorCodeEnum.INVALID_PARAMS.getCode(), ErrorCodeEnum.INVALID_PARAMS.getDesc(), "size should be in 1 ~ " + this.getMaxIdsSize(), null);
            return result;
        }
        
        boolean hasPrefix = StringUtils.isNotBlank(requestModel.getIdPrefix());
        boolean hasPostfix = StringUtils.isNotBlank(requestModel.getIdPostfix());
        List<String> ids = new ArrayList<>();
        for(int i = 0 ; i < requestModel.getSize(); i++) {
            String id = Objects.toString(snowFlakeIdGenerator.nextId());
            if(hasPrefix) {
                id = requestModel.getIdPrefix() + id;
            }
            if(hasPostfix) {
                id = id + requestModel.getIdPostfix();
            }
            ids.add(id);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("ids", ids);
        result.setMoudle(map);
        return result;
    }
    

    public int getMaxIdsSize() {
        return maxIdsSize;
    }

    

}
