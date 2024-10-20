package com.example.demo.api;

import com.example.demo.Entity.Consignment;
import com.example.demo.Service.ConsignmentService;
import com.example.demo.model.request.ConsignmentRequest;
import com.example.demo.model.request.KoiRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import com.example.demo.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consignment")
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class ConsignmentAPI {
    @Autowired
    private ConsignmentService consignmentService;


    @PostMapping("/create")
    public ResponseEntity createConsignment(@RequestBody KoiRequest koiRequest) {
        Consignment consignment = null;
        try {
            consignment = consignmentService.createConsignment(koiRequest);
            if (consignment == null) throw new BadRequestException("createConsignment is null");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(consignment);
    }

    @PutMapping("/waitingConsignment/{consignmentId}")
    public ResponseEntity waitingConsignment(@PathVariable long consignmentId) {
        Consignment consignment = null;
        try {
            consignment = consignmentService.waitingConsignment(consignmentId);
            if (consignment == null) throw new BadRequestException("waitingConsignment is null");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(consignment);
    }

    @GetMapping("getConsignment")
    public ResponseEntity getAllConsignment() {
        List<Consignment> consignments = consignmentService.getAllConsignments();
        return ResponseEntity.ok(consignments);
    }

    @PutMapping("/checkConsignment/{consignmentId}")
    public ResponseEntity checkConsignment(@PathVariable long consignmentId, ConsignmentRequest consignmentRequest) {
        Consignment consignment = null;
        try {
            consignment = consignmentService.checkConsignment(consignmentId, consignmentRequest);
            if (consignment == null) throw new BadRequestException("checkConsignment is null");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(consignment);
    }

    @PutMapping("/approveConsignment/{consignmentId}")
    public ResponseEntity approveConsignment(@PathVariable long consignmentId, ConsignmentRequest consignmentRequest) {
        Consignment consignment = null;
        try {
            consignment = consignmentService.approveConsignment(consignmentId, consignmentRequest);
            if (consignment == null) throw new BadRequestException("approveConsignment is null");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(consignment);
    }

    @GetMapping("getConsignment/{consignmentId}")
    public ResponseEntity getConsignmentById(@PathVariable long consignmentId) {
        Consignment consignment = null;
        try {
            consignment = consignmentService.selectConsignmentById(consignmentId);
            if (consignment == null) throw new BadRequestException("getConsignmentById is null");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(consignment);
    }

    @PutMapping("/consignedConsignment/{consignmentId}")
    public ResponseEntity consignedConsignment(@PathVariable long consignmentId, ConsignmentRequest consignmentRequest) {
        Consignment consignment = null;
        try {
            consignment = consignmentService.consignedConsignment(consignmentId, consignmentRequest);
            if (consignment == null) throw new BadRequestException("consignedConsignment is null");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(consignment);
    }

    @PutMapping("/cancelConsign/{consignmentId}")
    public ResponseEntity cancelConsign(@PathVariable long consignmentId) {
        Consignment consignment = null;
        try {
            consignment = consignmentService.cancelConsign(consignmentId);
            if (consignment == null) throw new BadRequestException("cancelConsign is null");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(consignment);
    }

    @PutMapping("/paidConsignment/{consignmentId}")
    public ResponseEntity paidConsignment(@PathVariable long consignmentId) {
        Consignment consignment = null;
        try {
            consignment = consignmentService.paidConsignment(consignmentId);
            if (consignment == null) throw new BadRequestException("paidConsignment is null");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(consignment);
    }

}
