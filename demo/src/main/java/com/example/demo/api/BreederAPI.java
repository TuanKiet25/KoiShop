package com.example.demo.api;

import com.example.demo.Entity.Breeder;
import com.example.demo.Service.BreederService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SecurityRequirement(name = "api")
public class BreederAPI {
    @Autowired
    BreederService breederService;

    //xuat nguon goc
    @GetMapping(value = "/api/breeder")
    public ResponseEntity getAllVarieties() {
        List<Breeder> Breeder = breederService.getAllBreeders();
        return ResponseEntity.ok(Breeder);
    }

    //tim ten nguon goc
    @GetMapping(value = "/api/breeder/{breederName}")
    public ResponseEntity getBreederByName(@PathVariable String breederName) {
        Breeder Breeder = breederService.getBreederNames(breederName);
        return ResponseEntity.ok(Breeder);
    }
}
