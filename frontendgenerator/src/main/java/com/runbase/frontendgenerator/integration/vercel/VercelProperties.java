package com.runbase.frontendgenerator.integration.vercel;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "vercel")
public class VercelProperties {
    
    private String url;
    private String token;

}