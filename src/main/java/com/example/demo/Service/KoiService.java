package com.example.demo.Service;

import com.example.demo.Entity.Breeder;
import com.example.demo.Entity.Koi;
import com.example.demo.Entity.Variety;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.request.KoiRequest;
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
    public Koi createKoi(KoiRequest koiRequest){
        Koi newKoi = new Koi();
        Breeder breeder = breederRepository.findByBreederName(koiRequest.getBreederName());
        if (breeder == null) throw new BadRequestException("Breeder does not exist!");

        Variety variety = varietyRepository.findByVarietyName(koiRequest.getVarietyName());
        if (variety == null) throw new BadRequestException("Variety does not exist!");

        newKoi.setKoiName(koiRequest.getKoiName());
        newKoi.setKoiSize(koiRequest.getKoiSize());
        newKoi.setKoiBorn(koiRequest.getKoiBorn());
        newKoi.setKoiGender(koiRequest.getKoiGender());
        newKoi.setPrice(koiRequest.getPrice());
        newKoi.setKoiDes(koiRequest.getKoiDes());
        newKoi.setKoiPrize(koiRequest.getKoiPrize());
        newKoi.setKoiStatus(koiRequest.getKoiStatus());
        newKoi.setBreeder(breeder);
        newKoi.setVariety(variety);

        return koiRepository.save(newKoi);
    }


    //read koi
    public List<Koi> getAllKoi(){
    List<Koi> kois= koiRepository.findAll();
    return kois;
    }


    //update  koi
    public Koi update(long koiId, KoiRequest koiRequest){
        Breeder breeder = breederRepository.findByBreederName(koiRequest.getBreederName());
        if (breeder == null) throw new BadRequestException("Breeder does not exist!");

        Variety variety = varietyRepository.findByVarietyName(koiRequest.getVarietyName());
        if (variety == null) throw new BadRequestException("Variety does not exist!");

        Koi oldkoi = getKoiById(koiId);
        oldkoi.setKoiName(koiRequest.getKoiName());
        oldkoi.setKoiSize(koiRequest.getKoiSize());
        oldkoi.setKoiBorn(koiRequest.getKoiBorn());
        oldkoi.setKoiGender(koiRequest.getKoiGender());
        oldkoi.setPrice(koiRequest.getPrice());
        oldkoi.setKoiDes(koiRequest.getKoiDes());
        oldkoi.setKoiPrize(koiRequest.getKoiPrize());
        oldkoi.setKoiStatus(koiRequest.getKoiStatus());
        oldkoi.setBreeder(breeder);
        oldkoi.setVariety(variety);
        return koiRepository.save(oldkoi);
    }

    //delete
    public Koi deleteKoi(long id){
    Koi koi = getKoiById(id);
        koi.setDeleted(true);
        return koiRepository.save(koi);
    }

    //findByKoiName
    public List<Koi> findByName(String koiName){
        List<Koi> koiList = koiRepository.findByKoiName(koiName);
        if(koiList.isEmpty()) throw new BadRequestException("Koi not found!");
        return koiList;
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

    //Find by id
    public Koi getKoiById(long id){
        Koi koi = koiRepository.findById(id);
        if(koi == null) throw new EntityNotFoundException();
        return koi;
    }

}
