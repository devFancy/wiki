package com.internal.team.wiki.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


// Cross-Origin Resource Sharing (CORS)를 설정하기 위한 설정 클래스
// 다른 도메인으로부터의 요청을 허용할지 여부를 설정하는 메커니즘을 제공
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String ALLOWED_METHOD_NAMES = "GET,HEAD,POST,PATCH,PUT,DELETE,TRACE,OPTIONS";

    private final List<String> allowOriginUrlPatterns;

    public WebConfig(@Value("${cors.allow-origin.urls}") final List<String> allowOriginUrlPatterns) {
        this.allowOriginUrlPatterns = allowOriginUrlPatterns;
    }

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        String[] patterns = allowOriginUrlPatterns.toArray(String[]::new);

        registry.addMapping("/api/**")
                .allowedMethods(ALLOWED_METHOD_NAMES.split(","))
                .allowedOrigins(patterns)
                .allowCredentials(true);
    }
}
