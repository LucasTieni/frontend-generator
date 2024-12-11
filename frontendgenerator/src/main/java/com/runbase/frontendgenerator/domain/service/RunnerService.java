package com.runbase.frontendgenerator.domain.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runbase.frontendgenerator.domain.utils.ZipHelper;
import com.runbase.frontendgenerator.integration.github.GitHubService;
import com.runbase.frontendgenerator.integration.github.POJO.GithubRepo;
import com.runbase.frontendgenerator.integration.vercel.VercelService;

import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.exception.ZipException;

@Service
@Slf4j
public class RunnerService {
	
	private static String homeDirectory = System.getProperty("user.home");
	private static String FILE_SEPARATOR = System.getProperty("file.separator");
	
	@Autowired
	private GitHubService gitHubService;
	
	@Autowired
	private VercelService vercelService;
	
	public void initialziePrompt(String repoTemplate, String repoName) throws IOException, InterruptedException, ExecutionException, ZipException {
		GithubRepo githubRepo = gitHubService.createRepoFromTemplate(repoTemplate, repoName);
		
		String directoryPath = cloneRepo(githubRepo.getName());
		
//		gitHubService.createUpdateFileRepo("src/pages/home.js", repoName, new String(createFile(directoryPath)));
		
		vercelService.createProject(githubRepo.getName(), githubRepo.getFull_name());
		
		vercelService.createDeployment(githubRepo);
	}
	
	private byte[] createFile(String directoryPath){
		byte[] fileData = "Pankaj Kumar".getBytes();
		
		try {
			FileOutputStream fos = new FileOutputStream(directoryPath + FILE_SEPARATOR + "home.js");
			fos.write(fileData);
			fos.flush();
			fos.close();
			
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return Base64.getEncoder().encode(fileData);
	}
	
	public String cloneRepo(String repoName) throws IOException, ZipException {
		try {
			byte[] repoContent = gitHubService.getRepoContent(repoName);
			
			ZipHelper zipHelper = new ZipHelper();
			String unzipPath = zipHelper.unzip(repoContent, homeDirectory, repoName);
			
			log.info(String.format("Repository %s cloned successfully on %s",repoName, unzipPath));
			return unzipPath;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(String.format("Error while clonning repository %s",repoName));
			return null;
		}
	}
	
	public void deleteProject(String repoName) throws InterruptedException, IOException {
		try {
			gitHubService.deleteRepo(repoName);
			
			vercelService.deleteProject(repoName);	
		} catch (Exception e) {
			log.error("error while deleting");
		}
		
	}
	

	
}
