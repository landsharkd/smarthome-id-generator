package com.phicomm.smarthome.generator.model.request.id;

import com.phicomm.smarthome.generator.model.request.AbstractBaseRequest;

/**
 * package: com.phicomm.r1mini.request
 * class: IdsGeneratorRequest.java
 * date: 2018年6月12日 下午4:04:47
 * author: wen.xia
 * description:
 */
public class IdsGeneratorRequest extends AbstractBaseRequest{

    private static final long serialVersionUID = 3237727246611965460L;

    //生产id的数量，必填
    private int size;

    //生成id的统一前缀
    private String idPrefix;
    
   //生成id的统一后缀
    private String idPostfix;


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getIdPrefix() {
        return idPrefix;
    }

    public void setIdPrefix(String idPrefix) {
        this.idPrefix = idPrefix;
    }

    public String getIdPostfix() {
        return idPostfix;
    }

    public void setIdPostfix(String idPostfix) {
        this.idPostfix = idPostfix;
    }   

}
