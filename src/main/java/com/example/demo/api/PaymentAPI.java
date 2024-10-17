package com.example.demo.api;

import com.example.demo.Entity.Payment;
import com.example.demo.Service.PaymentService;
import com.example.demo.model.request.PaymentRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class PaymentAPI
{
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity createPayment(@RequestBody PaymentRequest paymentRequest){
        Payment payment = paymentService.createPayment(paymentRequest);
        return ResponseEntity.ok(payment);
    }
    @GetMapping("/get")
    public ResponseEntity getAll(){
        List<Payment> paymentsList = paymentService.getAllPayments();
        return ResponseEntity.ok(paymentsList);
    }

}
