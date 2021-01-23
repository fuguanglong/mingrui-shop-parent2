package com.baidu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName TestSpringBootApplication
 * @Description: TODO
 * @Author fuguanglong
 * @Date 2020/12/28
 * @Version V1.0
 **/
@SpringBootApplication
@MapperScan("com.baidu.shop.mapper")
@EnableEurekaClient
public class RunXXXApplication {
    public static void main(String[] args){
        SpringApplication.run(RunXXXApplication.class);
    }
}
