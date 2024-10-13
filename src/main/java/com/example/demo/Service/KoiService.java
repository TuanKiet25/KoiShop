package com.example.demo.Service;

import com.example.demo.Entity.Breeder;
import com.example.demo.Entity.Koi;
import com.example.demo.Entity.Variety;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.BreederRepository;
import com.example.demo.repository.KoiRepository;
import com.example.demo.repository.VarietyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KoiService  {
    @Autowired
    KoiRepository koiRepository;
    @Autowired
    BreederRepository breederRepository;
    @Autowired
    VarietyRepository varietyRepository;

    //Create Koi
    public Koi createKoi(Koi koi){
        Koi newKoi = koiRepository.save(koi);
        return newKoi;
    }
    //read koi
    public List<Koi> getAllKoi(){
    List<Koi> kois= koiRepository.findAll();
    return kois;
    }


    //update  koi
    public Koi update(long koiId, Koi koi){
        Koi oldkoi = getKoiById(koiId);
        oldkoi.setKoiName(koi.getKoiName());
        oldkoi.setKoiSize(koi.getKoiSize());
        oldkoi.setKoiBorn(koi.getKoiBorn());
        oldkoi.setKoiGender(koi.getKoiGender());
        oldkoi.setPrice(koi.getPrice());
        oldkoi.setKoiDes(koi.getKoiDes());
        oldkoi.setKoiPrize(koi.getKoiPrize());
        oldkoi.setKoiStatus(koi.getKoiStatus());
        oldkoi.setBreeder(koi.getBreeder());
        oldkoi.setVariety(koi.getVariety());
        return koiRepository.save(oldkoi);
    }
    //delete
    public Koi deleteKoi(long id){
    Koi koi = getKoiById(id);
        koi.setDeleted(true);
        return koiRepository.save(koi);
    }
    //findByKoiName
    public Koi findByName(String koiName){
        Koi koi = koiRepository.findByKoiName(koiName);
        if(koi == null) throw new EntityNotFoundException();
        return koi;
    }
    //findKoiByBreeder
    public List<Koi> findByBreeder(String breederName){
    Breeder breeder = breederRepository.findByBreederName(breederName);
    if(breeder == null) throw new BadRequestException("Breeder does not exist!");
    List<Koi> koiBreederList =koiRepository.findByBreeder(breeder);
    return koiBreederList;
    }
    //Sorted by price Asc
    public List<Koi> sortedByPriceAsc(){
        return koiRepository.findAllByOrderByPriceAsc();
    }
    //Sorted by price Desc
    public List<Koi> sortedByPriceDesc(){
        return koiRepository.findAllByOrderByPriceDesc();
    }
    //Find by Variety
    public List<Koi> findByVariety(String varietyName){
       Variety variety = varietyRepository.findByVarietyName(varietyName);
       if(variety == null) throw new BadRequestException("Variety does not exist!");
       List<Koi> koiListVariety = koiRepository.findByVariety(variety);
        return koiListVariety;
    }
    public Koi getKoiById(long id){
        Koi koi = koiRepository.findById(id);
        if(koi == null) throw new EntityNotFoundException();
        return koi;
    }

}
