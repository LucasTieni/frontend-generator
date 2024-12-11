package com.runbase.frontendgenerator.integration.github.POJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubRepo{
    
	private Long id;
	private String name;
    private String full_name;
    private String html_url;
    private String default_branch;
}
