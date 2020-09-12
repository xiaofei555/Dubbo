package com.atguigu.gmall;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ImportResource;

/*
* 1、导入依赖：
*   1）、导入dubbo-starter
*   2）、导入dubbo的其他依赖
*
*
* SpringBoot与dubbo整合的三种方式：
* 1）、导入dubbo-starter，在application.properties配置属性，使用@Service【暴露服务】使用@Reference【引用服务】
* 2）、保留dubbo xml配置文件;
* 		导入dubbo-starter，使用@ImportResource导入dubbo的配置文件即可
* 3）、使用注解API的方式：
* 		将每一个组件手动创建到容器中,让dubbo来扫描其他的组件

* */
//@EnableDubbo //开启基于注解的dubbo的功能,SpringBoot与dubbo整合第一种方式
//@ImportResource(locations = "classpath:provider.xml")//SpringBoot与dubbo整合第二种方式
@EnableDubbo(scanBasePackages = "com.atguigu.gmall")//SpringBoot与dubbo整合第三种方式
@EnableHystrix //开启服务容错，Hystrix 旨在通过控制那些访问远程系统、服务和第三方库的节点，从而对延迟和故障提供更强大的容错能力
@SpringBootApplication
public class BootUserServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootUserServiceProviderApplication.class, args);
    }

}
