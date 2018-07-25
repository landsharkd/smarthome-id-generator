package com.phicomm.smarthome.generator.processor;
/**
 * package: com.phicomm.smarthome.generator.processor
 * class: IProcessor.java
 * date: 2018年6月15日 下午4:01:24
 * author: wen.xia
 * description:
 */
public interface IProcessor<T, Q> {
    public void processPost(T t, Q q);
    public void processPre(Q q);
}
