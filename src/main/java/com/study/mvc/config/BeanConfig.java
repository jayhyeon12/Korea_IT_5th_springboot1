package com.study.mvc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Bean 수동 등록(2개 이상 가능)

@Configuration
public class BeanConfig {

    @Bean // @Configuration 이 있는 클래스에서만 사용 가능
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
