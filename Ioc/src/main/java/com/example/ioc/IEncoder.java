package com.example.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public interface IEncoder {
    @Bean
    String encode(String msg);
}
