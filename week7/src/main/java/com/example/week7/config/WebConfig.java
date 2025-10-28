package com.example.week7.config;

import com.example.week7.common.jwt.JwtInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/users",                 // 회원가입
                        "/auth",                  // 로그인
                        "/error",                 // 스프링 기본 에러 핸들러
                        "/swagger-ui.html",       // Swagger 메인 HTML
                        "/swagger-ui/**",         // Swagger 정적 리소스
                        "/v3/api-docs/**",        // Swagger OpenAPI JSON
                        "/webjars/**"             // Swagger 의존성 내부 리소스
                );
    }
}