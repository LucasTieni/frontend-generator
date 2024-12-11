package com.runbase.frontendgenerator.domain.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "github")
public class StrapiProperties {
    
    private String url;
    private String token;
    private String owner;

}