package com.runbase.frontendgenerator.integration.vercel.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Deployment {
	
	private String name;
	private GitSource gitSource;
	private GitMetadata gitMetadata;
	private ProjectSettings projectSettings;
	
	@Getter
	@Setter
	@Builder
	public static class GitSource{
		private String ref;
		private Long repoId;
		private String type;
	}
	
	@Getter
	@Setter
	public static class GitMetadata{
		private String remoteUrl;
		
		public GitMetadata (String remoteUrl) {
			this.remoteUrl = remoteUrl; 
		}
	}
	
	@Getter
	@Setter
	public static class ProjectSettings{
        private String devCommand;
        private String installCommand;
        private String buildCommand;
        private String outputDirectory;
        private String rootDirectory;
        private String framework;
        
        public ProjectSettings() {
        	this.devCommand = null;
        	this.installCommand = null;
        	this.buildCommand = null;
        	this.outputDirectory = null;
        	this.rootDirectory = null;
        	this.framework = "nextjs";
        }
    }
	
	
}


	

	
	

