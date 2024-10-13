package com.example.demo.api;

import com.example.demo.Entity.Breeder;
import com.example.demo.Service.BreederService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/breeder")
@SecurityRequirement(name = "api")

public class BreederAPI {
    @Autowired
    BreederService breederService;

    @GetMapping("/{breederName}")
    public ResponseEntity findBreederByName(@PathVariable String breederName){
        Breeder breederResult = breederService.findBreederByName(breederName);
        return ResponseEntity.ok(breederResult);
    }
    @GetMapping
    public ResponseEntity getAllBreeder(){
        List<Breeder> breederList = breederService.getAllBreeder();
        return ResponseEntity.ok(breederList);
    }
}
