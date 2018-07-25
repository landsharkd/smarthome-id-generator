package com.phicomm.smarthome.generator.model;
/**
 * package: com.phicomm.smarthome.generator.model
 * class: DebugInfo.java
 * date: 2018年6月15日 下午4:13:25
 * author: wen.xia
 * description:
 */
public class DebugInfo extends BaseDO{

    private static final long serialVersionUID = -6766448239559169966L;
    
    private long start;
    
    private long end;
    //ms
    private long realTime;
    
    public long getRealTime() {
        return realTime;
    }

    public void setRealTime(long realTime) {
        this.realTime = realTime;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
    
    
    
}
