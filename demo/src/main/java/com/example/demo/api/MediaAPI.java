package com.example.demo.api;

import com.example.demo.Service.ImageUploadingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@SecurityRequirement(name = "/api")
public class MediaAPI
{
    @Autowired
    ImageUploadingService imageUploadingService;


    @PostMapping
    public String upload(@RequestParam("file") MultipartFile multipartFile)
    {
        return imageUploadingService.upload(multipartFile);
    }
}
