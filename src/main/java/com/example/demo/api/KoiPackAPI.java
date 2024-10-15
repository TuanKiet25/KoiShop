package com.example.demo.api;

import com.example.demo.Entity.Koi;
import com.example.demo.Entity.KoiPack;
import com.example.demo.Service.KoiPackService;
import com.example.demo.model.request.KoiPackRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/koiPack")
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class KoiPackAPI {
    @Autowired
    KoiPackService koiPackService;


    @PostMapping("/create")
    public ResponseEntity create(@RequestBody KoiPackRequest koiPackRequest) {
        KoiPack koiPack = koiPackService.create(koiPackRequest);
        return ResponseEntity.ok(koiPack);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        List<KoiPack> koiPackList = koiPackService.getAll();
        return ResponseEntity.ok(koiPackList);
    }

    @PutMapping("/Update/{koiPackId}")
    public ResponseEntity update(@PathVariable long koiPackId, @RequestBody KoiPackRequest koiPackRequest) {
        KoiPack koiPack = koiPackService.update(koiPackId, koiPackRequest);
        return ResponseEntity.ok(koiPack);
    }

    @GetMapping("/search/{koiPackName}")
    public ResponseEntity searchByName(@PathVariable String koiPackName) {
        List<KoiPack> koiPackList = koiPackService.findByName(koiPackName);
        return ResponseEntity.ok(koiPackList);
    }

    @GetMapping("/sortedAsc")
    public ResponseEntity sortedKoiPackByPriceAsc() {
        List<KoiPack> sortedList = koiPackService.sortedByPriceAsc();
        return ResponseEntity.ok(sortedList);
    }

    @GetMapping("/sortedDesc")
    public ResponseEntity sortedKoiPackByPriceDesc() {
        List<KoiPack> sortedList = koiPackService.sortedByPriceDesc();
        return ResponseEntity.ok(sortedList);
    }

    @GetMapping("/search/{breederName}")
    public ResponseEntity searchByBreeder(@PathVariable String breederName){
        List<KoiPack> koiPackList = koiPackService.findKoiPacksByBreederName(breederName);
        return ResponseEntity.ok(koiPackList);
    }

    @GetMapping("/search/{varietyName}")
    public ResponseEntity searchByVariety(@PathVariable String varietyName){
        List<KoiPack> koiPackList = koiPackService.findKoiPacksByVarietyName(varietyName);
        return ResponseEntity.ok(koiPackList);
    }
}
