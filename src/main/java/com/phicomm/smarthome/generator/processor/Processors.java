package com.phicomm.smarthome.generator.processor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.phicomm.smarthome.generator.model.request.AbstractBaseRequest;
import com.phicomm.smarthome.generator.model.result.Result;

/**
 * package: com.phicomm.smarthome.generator.processor
 * class: Processors.java
 * date: 2018年6月15日 下午4:23:30
 * author: wen.xia
 * description:
 */
@Component
public class Processors implements IBaseProcessor, InitializingBean{
    
    private List<IBaseProcessor> list = new ArrayList<>();

    @Override
    public void processPost(Result<?> t, AbstractBaseRequest q) {
        for(int i = list.size() - 1; i >= 0; i--) {
            list.get(i).processPost(t, q);
        }
    }

    @Override
    public void processPre(AbstractBaseRequest q) {
        for(int i = 0 ; i < list.size(); i++) {
            list.get(i).processPre(q);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        list.add(new DebugProcessor());
        list.add(new ErrorCodeMapingProcessor());
    }
}
