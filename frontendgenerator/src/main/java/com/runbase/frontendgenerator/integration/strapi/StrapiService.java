package com.runbase.frontendgenerator.integration.strapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.runbase.frontendgenerator.integration.github.POJO.GithubRepo;
import com.runbase.frontendgenerator.integration.vercel.pojo.Deployment;
import com.runbase.frontendgenerator.integration.vercel.pojo.Deployment.GitMetadata;
import com.runbase.frontendgenerator.integration.vercel.pojo.Deployment.GitSource;
import com.runbase.frontendgenerator.integration.vercel.pojo.Deployment.ProjectSettings;
import com.runbase.frontendgenerator.integration.vercel.pojo.Project;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StrapiService {
	
//	@Autowired
//	private RestTemplate restTemplate;
//	
//	@Autowired
//	private StrapiProperties strapiProperties;
	
//	public void createProject(String contentyType) {
//		String url = strapiProperties.getUrl() + "/v9/projects";
//		
//		Project project = new Project(projectName, "nextjs", githubRepoFullName, "github");
//		
//		HttpEntity<?> request = new HttpEntity<>(project, getDefautHeaders());
//		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
//		response.getBody();
//		
//		if (response.getStatusCode() == HttpStatus.OK) {
//			log.info(String.format("The project %s was successfuly created on Vercel", projectName));
//		} else if  (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
//			log.error(String.format("Error creating project %s in Vercel. Bad Request", projectName));
//		} else if  (response.getStatusCode() == HttpStatus.FORBIDDEN) {
//			log.error(String.format("Error creating project %s in Vercel."
//					+ "You do not have permission to access this resource", projectName));
//		} else if  (response.getStatusCode() == HttpStatus.CONFLICT) {
//			log.error(String.format("Error creating project %s in Vercel. "
//					+ "A project with the provided name already exists", projectName));
//		} else if  (response.getStatusCode() == HttpStatus.PAYMENT_REQUIRED) {
//			log.error(String.format("Error creating project %s in Vercel. "
//					+ "The account was soft-blocked for an unhandled reason or"
//					+ "	The account is missing a payment so payment method must be updated", projectName));
//		}
//	}
	
//	public void createDeployment(GithubRepo githubRepo) {
//		String url = vercelProperties.getUrl() + "v13/deployments/";
//		
//		Deployment deployment = Deployment.builder()
//			.name(githubRepo.getName())
//			.gitSource(GitSource.builder()
//				.ref(githubRepo.getDefault_branch())
//				.repoId(githubRepo.getId())
//				.type("github")
//				.build())
//			.gitMetadata(new GitMetadata(githubRepo.getHtml_url()))
//			.projectSettings(new ProjectSettings())
//			.build();
//		
//		HttpEntity<?> request = new HttpEntity<>(deployment, getDefautHeaders());
//		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
//		
//		if (response.getStatusCode() == HttpStatus.OK) {
//			log.info(String.format("Deployment %s created in Vercel successfully", githubRepo.getName()));
//		} else { 
//			log.error(String.format("Error while creating deployment %s in Vercel", githubRepo.getName()));
//		}
//	}
//	
//	public void deleteProject(String projectName) {
//		String url = vercelProperties.getUrl() + "v9/projects/" + projectName;
//		
//		HttpEntity<?> request = new HttpEntity<>(getDefautHeaders());
//		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
//		
//		if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
//			log.info(String.format("Project %s deleted on Vercel", projectName));
//		} else if (response.getStatusCode() == HttpStatus.BAD_REQUEST) { 
//			log.error(String.format("Error while deleting project %s on Vercel", projectName));
//		} else if (response.getStatusCode() == HttpStatus.FORBIDDEN) {
//			log.error(String.format("Error deleting project %s in Vercel."
//					+ "You do not have permission to access this resource", projectName));
//		}
//	}
//	
//	private HttpHeaders getDefautHeaders() {
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Authorization", "Bearer "+ strapiProperties.getToken());
//		return headers;
//	}
	
}
