package com.ino.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /*
        WebMvcConfigurer
        인터셉터 등록
        리소스 핸들링
        뷰 리졸버 세팅
        메세지 변환
        등등
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        // servlet-context.xml - <mvc:resource mapping="/upload/**" location="file:///upload/"/>
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///upload/");
    }

}
