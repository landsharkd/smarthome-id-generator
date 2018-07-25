package com.phicomm.smarthome.generator.model.request;

/**
 * package: com.phicomm.r1mini.request
 * class: IdsGeneratorRequest.java
 * date: 2018年6月12日 下午4:04:47
 * author: wen.xia
 * description:
 */
public abstract class AbstractMoudleBaseRequest<T> extends AbstractBaseRequest{

    private static final long serialVersionUID = -6596755758115884953L;
    
    private T moudle;

    public T getMoudle() {
        return moudle;
    }

    public void setMoudle(T moudle) {
        this.moudle = moudle;
    }

  
    
}
