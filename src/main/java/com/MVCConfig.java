package com;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.twovet.base.bean.TwoVetProperties;

@Configuration
public class MVCConfig implements WebMvcConfigurer{
	
	@Autowired
	TwoVetProperties prop;
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory(prop.getUploadDir(), registry);
    }
     
    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();
         
        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
        String upload= prop.getDocBase();
         
        registry.addResourceHandler("/" + upload + "/**").addResourceLocations("file:/"+ uploadPath + "/");
    }
}
