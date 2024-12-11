package com.runbase.frontendgenerator.integration.github;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "github")
public class GitHubProperties {
    
    private String url;
    private String token;
    private String owner;

}