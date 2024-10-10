package com.example.demo.api;

import com.example.demo.Entity.Variety;
import com.example.demo.Service.VarietyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "api")
public class VarietyAPI
{
    @Autowired
    VarietyService varietyService;
    // add giong ca moi
    @GetMapping(value = "/api/variety")
    public ResponseEntity getAllVarieties(){
        List<Variety> Variety = varietyService.getAllVariety();
        return ResponseEntity.ok(Variety);
    }

    @PostMapping(value = "/api/variety")
    public ResponseEntity createNewVariety(@RequestBody Variety variety){
        Variety newVariety = varietyService.createNewVariety(variety);
        return ResponseEntity.ok(newVariety);
    }
    @DeleteMapping(value = "/api/variety/{id}")
    public ResponseEntity deleteVeriety(@PathVariable long id)
    {
        varietyService.deleteVariety(id);
        return ResponseEntity.ok().build();
    }
}
