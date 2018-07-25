package com.phicomm.smarthome.generator.id;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * package: com.phicomm.r1mini.biz
 * class: SnowFlakeIdGenerator.java
 * date: 2018年6月12日 上午11:22:55
 * author: wen.xia
 * description:
 */

@Component
public class SnowFlakeIdGenerator implements InitializingBean{
    
    private Logger log = Logger.getLogger(this.getClass().getSimpleName());
    
    /**
     * 起始的时间戳
     */
    private  static long START_STMP = 1528782179721L;

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 12; //序列号占用的位数
    private final static long MACHINE_BIT = 5;   //机器标识占用的位数
    private final static long DATACENTER_BIT = 5;//数据中心占用的位数

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    @Value("${datacenterId}")
    private long datacenterId;  //数据中心
    @Value("${machineId}")
    private long machineId;     //机器标识
    private long sequence = 0L; //序列号
    private long lastStmp = -1L;//上一次时间戳

    /*public SnowFlakeIdGenerator() {
        try {
            log.info(String.format("org datacenterId=%s @value", datacenterId));
            if(StringUtils.isNotBlank(System.getProperty("datacenterId"))){
                this.datacenterId = Long.parseLong(System.getProperty("datacenterId"));
                log.info(String.format("get datacenterId=%s from System.getProperty", datacenterId));
            }else if(StringUtils.isNotBlank(System.getenv("datacenterId"))) {
                this.datacenterId = Long.parseLong(System.getenv("datacenterId"));
                log.info(String.format("get datacenterId=%s from System.getenv", datacenterId));
            }
            
            if(StringUtils.isNotBlank(System.getProperty("machineId"))){
                this.machineId = Long.parseLong(System.getProperty("machineId"));
                log.info(String.format("get machineId=%s from System.getProperty", machineId));
            }else if(StringUtils.isNotBlank(System.getenv("machineId"))) {
                this.machineId = Long.parseLong(System.getenv("machineId"));
                log.info(String.format("get machineId=%s from System.getenv", machineId));
            }
            
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("datacenterId or machineId of SystemProperties is illegal: datacenterId = %s, machineId = %s", System.getProperty("datacenterId"), System.getProperty("machineId")));
        }
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
    }*/
    
    public void afterPropertiesSet() {
        log.info(String.format("AfterPropertiesSet datacenterId=%s,  machineId = %s", datacenterId,machineId));
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenterId can't be greater than %s or less than 0", MAX_DATACENTER_NUM));
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException(String.format("machineId can't be greater than %s or less than 0", MAX_MACHINE_NUM));
        }
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStmp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTMP_LEFT //时间戳部分
                | datacenterId << DATACENTER_LEFT       //数据中心部分
                | machineId << MACHINE_LEFT             //机器标识部分
                | sequence;                             //序列号部分
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }
    
    

    public long getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(long datacenterId) {
        this.datacenterId = datacenterId;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public static void main(String[] args) {
        SnowFlakeIdGenerator snowFlake = new SnowFlakeIdGenerator();
        START_STMP = System.currentTimeMillis();
        snowFlake.setDatacenterId(0);
        snowFlake.setMachineId(0);
        long start = System.currentTimeMillis();
        for (int i = 0; i < (1 << 12); i++) {
            System.out.println(snowFlake.nextId());
        }
        long ms = System.currentTimeMillis() - start;
        System.out.println("take time : " +  ms + " ms");
        System.out.println("gen speed : " +  (4096 * 1.0 / ms) * 1000 + "/s");
        System.out.println(start);
        System.out.println(System.currentTimeMillis());
        System.out.println(Objects.toString(snowFlake.nextId()).length());
        System.out.println(Long.MAX_VALUE);
    }
}
