package com.phicomm.smarthome.generator.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * package: com.phicomm.smarthome.generator.common
 * class: BaseDO.java
 * date: 2018年6月13日 上午9:32:46
 * author: wen.xia
 * description:
 */
@JsonInclude(Include.NON_NULL)
public abstract class BaseDO implements Serializable{

    private static final long serialVersionUID = -2456641772638329189L;
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
    
}
