package com.example.demo.api;

import com.example.demo.Entity.Koi;
import com.example.demo.Service.KoiService;
import com.example.demo.model.request.KoiRequest;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/koi")
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class KoiAPI {
    @Autowired
    KoiService koiService;


@PostMapping("/createKoi")
    public ResponseEntity createKoi(@RequestBody KoiRequest koiRequest){
    Koi newKoi = koiService.createKoi(koiRequest);
    return ResponseEntity.ok(newKoi);
}

@GetMapping("getAllKoi")
    public ResponseEntity getAllKoi(){
    List<Koi> kois = koiService.getAllKoi();
    return ResponseEntity.ok(kois);
}
@PutMapping("/update/{koiId}")
public ResponseEntity updateKoi(@PathVariable long koiId, @RequestBody KoiRequest koiRequest){
    Koi updateKoi = koiService.update(koiId, koiRequest);
    return ResponseEntity.ok(updateKoi);
}

@DeleteMapping("/{koiId}")
    public ResponseEntity deleteKoi(@PathVariable long koiId){
    Koi deletedKoi = koiService.deleteKoi(koiId);
    return ResponseEntity.ok(deletedKoi);
}

@GetMapping("/{koiName}")
    public ResponseEntity findKoiByName(@PathVariable String koiName){
    List<Koi> koiResultList = koiService.findByName(koiName);
    return ResponseEntity.ok(koiResultList);
}
@GetMapping("/breeder/{breederName}")
    public ResponseEntity findKoiByBreeder(@PathVariable String breederName) {
    List<Koi> searchKoiList = koiService.findByBreeder(breederName);
    return ResponseEntity.ok(searchKoiList);
}
@GetMapping("/sortedAsc")
    public ResponseEntity sortedKoiByPriceAsc(){
    List<Koi> sortedList = koiService.sortedByPriceAsc();
    return ResponseEntity.ok(sortedList);
}
@GetMapping("/sortedDesc")
    public ResponseEntity sortedKoiByPriceDesc(){
    List<Koi> sortedList = koiService.sortedByPriceDesc();
    return ResponseEntity.ok(sortedList);
    }
@GetMapping("/variety/{varietyName}")
    public  ResponseEntity findKoiByVariety(@PathVariable String varietyName){
    List<Koi> searchKoiList = koiService.findByVariety(varietyName);
    return ResponseEntity.ok(searchKoiList);
}

}
