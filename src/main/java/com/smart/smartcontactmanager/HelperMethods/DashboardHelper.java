package com.smart.smartcontactmanager.HelperMethods;

import com.smart.smartcontactmanager.Services.UserRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.UUID;

public class DashboardHelper {
//    private final String upload_path=new ClassPathResource("static/images/").getFile().getAbsolutePath();
    String upload_path = "/Users/dineshgvns/MAC/Java_Projects/Projects/SmartContactManager/src/main/resources/static/images/Database/";
    public DashboardHelper() throws IOException {
    }

    public String processImageFile(MultipartFile imageFile){
        try{
            String originalFileName=imageFile.getOriginalFilename();
            String FileExtension=originalFileName.substring(originalFileName.lastIndexOf("."));
            String newFileName= UUID.randomUUID().toString()+FileExtension;


            Path CompleteFilePath= Paths.get(upload_path,newFileName);
//            System.out.println(CompleteFilePath);
            Files.write(CompleteFilePath,imageFile.getBytes());
            return newFileName;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public String getImage(String fileName){
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/user/").path(fileName).toUriString();
    }

    public void deleteImage(String fileName) throws IOException {
        if(!fileName.equals("default.png")){
            Path path=Paths.get(upload_path,fileName);
            if(Files.exists(path)){
                Files.delete(path);
            }
        }

    }
}
