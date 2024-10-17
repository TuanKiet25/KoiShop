package com.example.demo.api;

import com.example.demo.Entity.KoiOrder;
import com.example.demo.Service.OrderService;
import com.example.demo.model.request.OrderRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class OrderAPI {
    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestBody OrderRequest orderRequest) throws Exception {
        String vnPayUrl = orderService.createUrl(orderRequest);
        return ResponseEntity.ok(vnPayUrl);
    }
    @GetMapping("/get")
    public ResponseEntity getAll(){
        List<KoiOrder> koiOrderList = orderService.getAllOrder();
        return ResponseEntity.ok(koiOrderList);
    }
}
