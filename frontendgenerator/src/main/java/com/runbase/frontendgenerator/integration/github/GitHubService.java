package com.runbase.frontendgenerator.integration.github;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.runbase.frontendgenerator.integration.github.POJO.Commit;
import com.runbase.frontendgenerator.integration.github.POJO.Committer;
import com.runbase.frontendgenerator.integration.github.POJO.GithubRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GitHubService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private GitHubProperties gitHubProperties;
	
	public void createUpdateFileRepo(String filePathRepo, String repoName, String fileContent) {
		String url = gitHubProperties.getUrl() + "repos/" + gitHubProperties.getOwner() + "/" +
				repoName + "/contents/" + filePathRepo;
		
		Committer committer = Committer.builder()
			.name("programName")
			.email("lucastieni@gmail.com")
			.build();
		
		Commit commit = Commit.builder()
			.message("creating file xxx")
			.committer(committer)
			.content(fileContent)
			.build();
		
		HttpEntity<?> request = new HttpEntity<>(commit, getDefautHeaders());
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
		
		if (response.getStatusCode() == HttpStatus.CREATED) {
			log.info(String.format("Success creating file %s in repo %s", filePathRepo, repoName));
		} else { 
			log.error(String.format("Error creating file %s in repo %s", filePathRepo, repoName));
		}
	}
	
	public GithubRepo createRepoFromTemplate(String repoTemplate, String repoName) {
		String url = gitHubProperties.getUrl() + "repos/" + gitHubProperties.getOwner() + "/" +
				repoTemplate + "/generate";
		
		GithubRepo githubRepo = new GithubRepo();
		
		HashMap<String,Object> body = new HashMap<>();
		body.put("owner", gitHubProperties.getOwner());
		body.put("name", repoName);
		body.put("description", "repo created auto");
		body.put("include_all_branches", false);
		body.put("private", true);
		
		HttpEntity<?> request = new HttpEntity<>(body, getDefautHeaders());
		
		try {
			ResponseEntity<GithubRepo> response = restTemplate.exchange(url, HttpMethod.POST, request, GithubRepo.class);
			
//			if (response.getStatusCode() == HttpStatus.CREATED) {
				githubRepo = response.getBody();
				log.info(String.format("Repo %s created from template %s", repoName, repoTemplate));
				return githubRepo;
//			}	
		} catch (HttpClientErrorException  e) {
			e.printStackTrace();
			log.error(String.format("Error while creating repo %s create from template %s", repoName, repoTemplate));
			return githubRepo;	
		}
		
	}
	
	public byte[] getRepoContent (String repoName) throws IOException {

			String url = gitHubProperties.getUrl() + "repos/" + gitHubProperties.getOwner() + "/" +
					repoName + "/zipball";
			
			Integer tries = 0;
			while (tries<10) {
				try {
					tries++;
					HttpEntity<String> request = new HttpEntity<String>(getDefautHeaders());
					ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, request, byte[].class);
					return response.getBody();
				} catch (Exception e) {
					log.error(String.format("Try %d: get repository content. %s", tries, e.getMessage()));
				}
			}
			log.error(String.format("Problem while downloading repository content."));
			return new byte[0];
	}
	
	public void deleteRepo(String repoName) {
		String url = gitHubProperties.getUrl() + "repos/" + gitHubProperties.getOwner() + "/" +
				repoName;
		
		HttpEntity<?> request = new HttpEntity<>(getDefautHeaders());
		
		try {
			restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
			
			log.info(String.format("Repo %s deleted", repoName));
			
		} catch (Exception e) {
			e.printStackTrace();
			log.error(String.format("Error while deleting repo %s", repoName));
		}
	}
	
//	public void deleteFile(String repoName, String filePathRepo) {
//		String url = gitHubProperties.getUrl() + "repos/" + gitHubProperties.getOwner() + "/" +
//				repoName + "/contents/" + filePathRepo;
//		
//		HttpEntity<?> request = new HttpEntity<>(getDefautHeaders());
//		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, request, String.class);
//		
//		if (response.getStatusCode() == HttpStatus.NO_CONTENT) {
//			log.info(String.format("Repo %s deleted", repoName));
//		} else { 
//			log.error(String.format("Error while deleting repo %s", repoName));
//		}
//	}
	
	private HttpHeaders getDefautHeaders() {
		HttpHeaders headers = new HttpHeaders();
//		headers.add("Accept", "application/vnd.github+json");
		headers.add("Authorization", "Bearer "+ gitHubProperties.getToken());
		headers.add("X-GitHub-Api-Version", "2022-11-28");
		return headers;
	}
	
}
