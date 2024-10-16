package com.example.demo.api;

import com.example.demo.Entity.Media;
import com.example.demo.Service.MediaService;
import com.example.demo.model.request.MediaRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
@CrossOrigin("*")
@SecurityRequirement(name = "api")

public class MediaAPI
{
    @Autowired
    private MediaService mediaService;

    @PostMapping("/create")
    public ResponseEntity createMedia (@RequestBody MediaRequest media)
    {
        Media mediaCreated = mediaService.createMedia(media);
        return ResponseEntity.ok(mediaCreated);
    }

    @GetMapping("/{url}")
    public ResponseEntity findMediaByUrl(@PathVariable MediaRequest url)
    {
        Media media = mediaService.findMediaByUrl(url);
        return ResponseEntity.ok(media);
    }

    @GetMapping
    public ResponseEntity getAllBreeder()
    {
        List<Media> mediaList = mediaService.getAllMedia();
        return ResponseEntity.ok(mediaList);
    }
}
