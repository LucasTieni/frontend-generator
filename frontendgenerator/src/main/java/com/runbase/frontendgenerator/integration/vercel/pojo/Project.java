package com.runbase.frontendgenerator.integration.vercel.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Project {
	
	private String name;
	private String framework;
	private GitRepository gitRepository;
	
	public Project(String name, String framework, String repo, String type) {
		this.name = name;
		this.framework = framework;
		this.gitRepository = new GitRepository(repo, type);
	}
	
	@Setter
	@Getter
	class GitRepository{
		private String repo;
		private String type;
		
		public GitRepository(String repo, String type) {
			this.setRepo(repo);
			this.type = type;
		}
	}
}

