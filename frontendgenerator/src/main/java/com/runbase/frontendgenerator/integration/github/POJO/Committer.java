package com.runbase.frontendgenerator.integration.github.POJO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Committer {
    private String name;
    private String email;
}
