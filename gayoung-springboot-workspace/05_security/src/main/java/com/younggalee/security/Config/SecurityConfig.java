package com.younggalee.security.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;

@EnableWebSecurity
@Controller
public class SecurityConfig {

    @Bean
    // 재정의하는 순간 기존설정적용안되기 때문에 기존거 사용하고 싶으면 다시 써줘야함.
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // URL 접근 제어 (인가 설정)
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/", "/login", "/signup", "/images/**").permitAll() // 별도의 인증처리 없이 허용하겠다.
                    .anyRequest().authenticated(); // 그밖에 모든 페이지는 접근제어
        });

        //로그인 처리 설정 (인증 설정)
        http.formLogin(Customizer.withDefaults()); // 기존 로그인 메커니즘

        return http.build(); //SecurityFilterChain 객체로 반환
    }
}
