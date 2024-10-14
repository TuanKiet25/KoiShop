package com.example.demo.api;

import com.example.demo.Service.KoiPackService;
import com.example.demo.model.request.KoiPackRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@SecurityRequirement(name = "api")
public class KoiPackAPI
{
    @Autowired
    private KoiPackService koiPackService;

    //tao anh koi
    @PostMapping(value = "/api/koipack", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity createNewMedia(@RequestPart KoiPackRequest koiPackRequest, @RequestPart List<MultipartFile> documentList) {
        return ResponseEntity.ok("ok test");
    }

    //tu document list upload document list to firebase sau do se co link url, them vao danh sanh media cua koi pack
}
