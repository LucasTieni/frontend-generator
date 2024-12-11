package com.runbase.frontendgenerator.integration.github.POJO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Commit{
	private String message;
    private Committer committer;
    private String content;
}
