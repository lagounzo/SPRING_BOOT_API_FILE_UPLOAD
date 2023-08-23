package com.upload.file.upload.services;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Nous devons exécuter init()la méthode de FilesStorageService(et aussi deleteAll()
 * si nécessaire)
 */

@SpringBootApplication
public class SpringBootUploadFilesApplication implements CommandLineRunner {
	
	@Resource
	FilesStorageService storageService;
	
	public static void main(String[] args) {
	    SpringApplication.run(SpringBootUploadFilesApplication.class, args);
	  }
	
	@Override
	  public void run(String... arg) throws Exception {
//	    storageService.deleteAll();
	    storageService.init();
	  }
}
