package com.faketri.market.infastructure.config.web;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class MultipartConfig {
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        return new MultipartConfigFactory().createMultipartConfig();
    }

 /*   @Bean
    public MappingJackson2HttpMessageConverter octetStreamJsonConverter() {
        MappingJackson2HttpMessageConverter converter = new MultipartJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(List.of(new MediaType("application", "octet-stream")));
        return converter;
    }*/
}
