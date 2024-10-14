package com.example.demo.api;

import com.example.demo.Entity.ServiceOption;
import com.example.demo.Entity.Store;
import com.example.demo.Service.OptionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "api")
public class OptionAPI {
    @Autowired
    OptionService optionService;

    @GetMapping(value = "/api/option")
    public ResponseEntity getAllOption(){
        List<ServiceOption> options= optionService.getAllOption();
        return ResponseEntity.ok(options);
    }

    @PostMapping(value="/api/option")
    public ResponseEntity createNewOption(@RequestBody ServiceOption option){
        ServiceOption newoption =optionService.createNewOption(option);
        return ResponseEntity.ok(newoption);
    }
    @DeleteMapping(value = "/api/option/{id}")
    public ResponseEntity deleteOption(@PathVariable long id){
        ServiceOption option = optionService.deleteOption(id);
        return ResponseEntity.ok(option);

    }
}
