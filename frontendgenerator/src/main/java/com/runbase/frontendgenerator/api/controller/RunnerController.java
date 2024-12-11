package com.runbase.frontendgenerator.api.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.runbase.frontendgenerator.domain.service.RunnerService;

import net.lingala.zip4j.exception.ZipException;

@RestController
@RequestMapping(value = "/runner")
public class RunnerController {
	
	@Autowired
	private RunnerService runnerService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void startRunner() throws IOException, InterruptedException, ExecutionException, ZipException{
		runnerService.initialziePrompt("runbase-template1", "client1-runbase-template1");
		
	}
	
	@DeleteMapping("/{repoName}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRunner(@PathVariable String repoName) throws IOException, InterruptedException{
		runnerService.deleteProject(repoName);
		
	}
}
