package com.sotogito.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /// URL 접근 제어(인가 설정)
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers(
                                "/",
                                "/login",
                                "/signup",
                                "/images/**"
                        ).permitAll()
                        .anyRequest().authenticated()
        );

        /// 로그인 처리 설정 (인증 설정) => 기본제공
//        http.formLogin(Customizer.withDefaults());
        /// 로그인 처리 설정(인증 설정) => 사용자 정의 로그인폼 설정
        http.formLogin(form ->
                form.loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("userId")
                        .passwordParameter("userPwd")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error")
        );

        /// 로그아웃 처리 설정(인증 설정)
        http.logout(logout ->
                logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
        );
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

}
