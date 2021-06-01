package com.twovet.base.common;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.twovet.base.bean.TwoVetProperties;

@Service
public class FileUploadUtil {

	@Autowired
	private TwoVetProperties prop;
	
	static TwoVetProperties properties;
	@PostConstruct
	void injectionProperties() {
		properties = prop;
	}
	public static void saveFile(String uploadDir, String fileName, 
			MultipartFile multipartFile) throws IOException {
		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
		try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        }  
	}
	
	public static boolean uploadFile(String fileName, 
			MultipartFile multipartFile) throws IOException {
		boolean success = false;
		String uploadDir = properties.getUploadDir();
		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
		try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            success = true;
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        }  
		return success;
	}
}
