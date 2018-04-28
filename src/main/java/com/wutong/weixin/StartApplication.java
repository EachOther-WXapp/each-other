package com.wutong.weixin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableMBeanExport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableAutoConfiguration(exclude={DataSourcePoolMetadataProvider.class})
@SpringBootApplication
@EnableMBeanExport(defaultDomain = "each-other")
@MapperScan("com.wutong.weixin.dao")
//@EnableSwagger2
public class StartApplication extends SpringBootServletInitializer {



//    org.springframework.boot.autoconfigure.jdbc.metadata.DataSourcePoolMetadataProvider
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class);
    }
}
