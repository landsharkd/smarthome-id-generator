package com.phicomm.smarthome.generator.processor;

import com.phicomm.smarthome.generator.model.request.AbstractBaseRequest;
import com.phicomm.smarthome.generator.model.result.Result;
import com.phicomm.smarthome.phihome.model.PhiHomeBaseResponse;

/**
 * package: com.phicomm.smarthome.generator.processor
 * class: DebugProcessor.java
 * date: 2018年6月15日 下午4:10:56
 * author: wen.xia
 * description:
 */
public class ErrorCodeMapingProcessor implements IBaseProcessor{

    @Override
    public void processPost(Result<?> t, AbstractBaseRequest q) {
        PhiHomeBaseResponse<Object> convert = new PhiHomeBaseResponse<>();
        convert.setCode(t.getErrorCode());
        convert.setMessage(t.getDesc());
        convert.setResult(t.getMoudle());
        t.setConvertMoudle(convert);
    }

    @Override
    public void processPre(AbstractBaseRequest q) {
    }

}
