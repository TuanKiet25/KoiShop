package com.example.demo.api;

import com.example.demo.Entity.Account;
import com.example.demo.Entity.Consignment;
import com.example.demo.Entity.enums.Role;
import com.example.demo.Service.AccountService;
import com.example.demo.Service.ConsignmentService;
import com.example.demo.Service.TokenService;
import com.example.demo.model.request.KoiRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consignment")
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class ConsignmentAPI
{
    @Autowired
    private ConsignmentService consignmentService;
    

    @PostMapping("/create")
    public ResponseEntity createConsignment(@RequestBody KoiRequest koiRequest) {
        Consignment consignment = new Consignment();
        try {
            consignment = consignmentService.createConsignment(koiRequest);
        } catch (BadRequestException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(consignment);
    }

}
