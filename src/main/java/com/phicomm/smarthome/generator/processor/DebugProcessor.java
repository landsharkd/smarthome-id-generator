package com.phicomm.smarthome.generator.processor;

import org.apache.log4j.Logger;

import com.phicomm.smarthome.generator.model.DebugInfo;
import com.phicomm.smarthome.generator.model.request.AbstractBaseRequest;
import com.phicomm.smarthome.generator.model.result.Result;

/**
 * package: com.phicomm.smarthome.generator.processor
 * class: DebugProcessor.java
 * date: 2018年6月15日 下午4:10:56
 * author: wen.xia
 * description:
 */
public class DebugProcessor implements IBaseProcessor{
    
    protected Logger log = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    public void processPost(Result<?> t, AbstractBaseRequest q) {
        Long rt = null;
        if(q != null && q.getDebugInfo() != null && q.getDebugInfo().getStart() > 0) {
            q.getDebugInfo().setEnd(System.currentTimeMillis());
            q.getDebugInfo().setRealTime(q.getDebugInfo().getEnd() - q.getDebugInfo().getStart());
            rt = q.getDebugInfo().getRealTime();
            if(q.isDebug()) {
                t.setDebugInfo(q.getDebugInfo());
            }
        }
        String methodName = "";
        StackTraceElement[] stes = Thread.currentThread().getStackTrace();
        if(stes != null && stes.length > 5) {
            StackTraceElement ste = stes[5];
            methodName = ste.getClassName().substring(ste.getClassName().lastIndexOf(".") + 1) + "." + ste.getMethodName();
        }
        if(t.isSuccess()) {
            log.info(String.format("Success to call %s[rt=%s], request : %s ,  result : %s", methodName, rt, q, t));
        }else {
            log.error(String.format("Failed to call %s[rt=%s], request : %s ,  result : %s", methodName, rt, q, t));
        }
    }

    @Override
    public void processPre(AbstractBaseRequest q) {
        if(q != null) {
            q.setDebugInfo(new DebugInfo());
            q.getDebugInfo().setStart(System.currentTimeMillis());
        }
    }

}
