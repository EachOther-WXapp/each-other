package com.wutong.weixin.utils.swagger;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@Configuration //spring配置文件中注入了，这里就可以不用注解
//@EnableWebMvc
@EnableSwagger2
//@Profile({"dev"})
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    //private static final String HOST = "zhichongjia.com:8443";
    private static final String HOST = null;


    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .host(HOST)
                .groupName("api")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wutong.weixin"))
                //.apis(RequestHandlerSelectors.any())//对所有api进行监控
                .paths(PathSelectors.any())//对所有路径进行监控
                .build()
                //.globalOperationParameters(setHeaderToken())
                //.securitySchemes(securitySchemes())
                //.securityContexts(securityContexts())
                .apiInfo(apiInfo());
    }


    private List<ApiKey> securitySchemes() {
        return Lists.newArrayList(
                new ApiKey("Authorization", "用户授权标识", "header"));
    }

    private List<SecurityContext> securityContexts() {
        return Lists.newArrayList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.any())
                        .build()
        );
    }


    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name(HttpHeaders.AUTHORIZATION).description("token...").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
                new SecurityReference("Authorization", authorizationScopes));
    }


    private ApiInfo apiInfo() {
        Contact contact = new Contact("wutong", "http://www.xiciwutong.xin/", "919964333@qq.com");
        ApiInfo apiInfo = new ApiInfo("EachOther系统", "API文档", "V1.0.0", "", contact, "EachOther系统", "http://www.xiciwutong.xin/", new ArrayList<>());
        return apiInfo;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}