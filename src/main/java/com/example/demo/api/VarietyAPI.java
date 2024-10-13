package com.example.demo.api;

import com.example.demo.Entity.Variety;
import com.example.demo.Service.VarietyService;
import com.example.demo.model.request.VarietyRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/variety")
@CrossOrigin("*")
@SecurityRequirement(name = "api")

public class VarietyAPI {

@Autowired
    VarietyService varietyService;

@PostMapping("/create")
    public ResponseEntity createVariety (@RequestBody VarietyRequest varietyRequest){
    Variety newVariety = varietyService.crateVariety(varietyRequest);
    return ResponseEntity.ok(newVariety);
}
}
