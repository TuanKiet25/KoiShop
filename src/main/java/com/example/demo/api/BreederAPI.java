package com.example.demo.api;

import com.example.demo.Entity.Breeder;
import com.example.demo.Service.BreederService;
import com.example.demo.model.request.BreederRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/breeder")
@CrossOrigin("*")
@SecurityRequirement(name = "api")

public class BreederAPI {
    @Autowired
    BreederService breederService;
    @PostMapping("/create")
    public ResponseEntity createBreeder(@RequestBody BreederRequest breederRequest){
        Breeder newBreeder = breederService.createBreeder(breederRequest);
        return ResponseEntity.ok(newBreeder);
    }

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
    @PutMapping("/update/{breederId}")
    public ResponseEntity updateBreeder(@PathVariable long breederId, @RequestBody BreederRequest breederRequest){
        Breeder breeder = breederService.updateBreeder(breederId,breederRequest);
        return ResponseEntity.ok(breeder);
    }
}
