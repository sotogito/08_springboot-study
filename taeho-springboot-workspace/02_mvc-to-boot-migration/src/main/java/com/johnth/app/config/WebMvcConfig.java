package com.johnth.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /*
        ## WebMvcConfigurer ##
        1) 인터셉터 등록
        2) 리소스 핸들링
        3) 뷰리졸버 세팅
        4) 메세지 변환
           등등
     */

    // 리소스 핸들링
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // servlet-context.xml - <mvc:resources mapping="/upload/**" location="file:///upload/"/>
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///upload/");
    }
}