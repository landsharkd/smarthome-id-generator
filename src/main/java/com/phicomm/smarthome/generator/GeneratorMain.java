package com.phicomm.smarthome.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 
 * package: com.phicomm.smarthome.generator
 * class: GeneratorMain.java
 * date: 2018年6月12日 下午4:29:29
 * author: wen.xia
 * description:
 */
@SpringBootApplication(scanBasePackages = { "com.phicomm.smarthome.**" })
@EnableEurekaClient
public class GeneratorMain {
    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(GeneratorMain.class, args);
    }
}
