package com.runbase.frontendgenerator.integration.strapi;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
//@Configuration
//@ConfigurationProperties(prefix = "strapi")
public class StrapiProperties {
    
    private String url;
    private String token;

}