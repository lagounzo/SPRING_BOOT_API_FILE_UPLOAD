package com.upload.file.upload.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service

public class FilesStorageServiceImpl implements FilesStorageService {

	
	private final Path root = Paths.get("uploads");
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		try {
			Files.createDirectories(root);
			
		} catch (IOException e) {
			// TODO: handle exception
			
			throw new RuntimeException("Ficher upload ne peut pas etre initialisé");
		}
		
		
	}

	@Override
	public void save(MultipartFile file) {
		// TODO Auto-generated method stub
		
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
			
		} catch (Exception e) {
			
			if (e instanceof FileAlreadyExistsException) {
				throw new RuntimeException(" ce ficher existe déja");
			}
			
			throw new RuntimeException(e.getMessage());
		}
		
	}

	@Override
	public Resource load(String filename) {
		// TODO Auto-generated method stub
		
		try {
			
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			
			if (resource.exists() || resource.isReadable()) {
				return resource;
				
			} else {
				throw new RuntimeException("ficher ne peut pas etre lu");
			}
			
		} catch (MalformedURLException e) {
			throw new RuntimeException("erreur: " + e.getMessage());
		}
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
		FileSystemUtils.deleteRecursively(root.toFile());
		
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
			
		} catch (IOException e) {
			throw new RuntimeException("Desolet le ficher ne peut pas etre load!");
		}
		

	}
	
	

}
