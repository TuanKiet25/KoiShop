package com.example.demo.Service;

import com.example.demo.Entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.request.BreederRequest;
import com.example.demo.model.request.KoiPackRequest;
import com.example.demo.model.request.VarietyRequest;
import com.example.demo.repository.BreederRepository;
import com.example.demo.repository.KoiPackRepository;

import com.example.demo.repository.VarietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KoiPackService {
    @Autowired
    KoiPackRepository koiPackRepository;
    @Autowired
    BreederRepository breederRepository;
    @Autowired
    VarietyRepository varietyRepository;

    //Create Koi Pack
    public KoiPack create(KoiPackRequest koiPackRequest){
        KoiPack koiPack= new KoiPack();

        koiPack.setKoiPackName(koiPackRequest.getKoiPackName());
        koiPack.setKoiPackBorn(koiPackRequest.getKoiPackBorn());
        koiPack.setKoiPackGender(koiPackRequest.getKoiPackGender());
        koiPack.setKoiPackSize(koiPackRequest.getKoiPackSize());
        koiPack.setKoiPackQuantity(koiPackRequest.getKoiPackQuantity());
        koiPack.setKoiPackStatus(koiPackRequest.getKoiPackStatus());
        koiPack.setPrice(koiPackRequest.getPrice());
        koiPack.setKoiPackDes(koiPackRequest.getKoiPackDes());
        //Update breeder
        List<Breeder> breederList = new ArrayList<>();
        for(BreederRequest breederRequest : koiPackRequest.getBreederRequestList()){
            Breeder breeder = new Breeder();
            breeder.setBreederName(breederRequest.getBreederName());
            breeder.setBreederPhone(breederRequest.getBreederPhone());
            breederList.add(breeder);
        }
        koiPack.setBreeders(breederList);
        //Update variety
        List<Variety> varieties= new ArrayList<>();
        for(VarietyRequest varietyRequest : koiPackRequest.getVarietyRequestList()){
            Variety variety = new Variety();
            variety.setVarietyDes(varietyRequest.getVarietyDes());
            variety.setVarietyName(varietyRequest.getVarietyName());
            varieties.add(variety);
        }
        koiPack.setVarieties(varieties);
        return koiPackRepository.save(koiPack);
    }

    //read koi
    public List<KoiPack> getAll(){
        List<KoiPack> koiPacks = koiPackRepository.findAll();
        List<KoiPack> koiPackList = new ArrayList<>();
        for(KoiPack koiPack : koiPacks){
            if(koiPack.isDeleted() == false){
                koiPackList.add(koiPack);
            }
        }
        return koiPackList;
    }

    //Update koi
    public KoiPack update(long id, KoiPackRequest koiPackRequest){

        KoiPack koiPack = getKoiPackById(id);
        koiPack.setKoiPackName(koiPackRequest.getKoiPackName());
        koiPack.setKoiPackBorn(koiPackRequest.getKoiPackBorn());
        koiPack.setKoiPackGender(koiPackRequest.getKoiPackGender());
        koiPack.setKoiPackSize(koiPackRequest.getKoiPackSize());
        koiPack.setKoiPackQuantity(koiPackRequest.getKoiPackQuantity());
        koiPack.setKoiPackStatus(koiPackRequest.getKoiPackStatus());
        koiPack.setPrice(koiPackRequest.getPrice());
        koiPack.setKoiPackDes(koiPackRequest.getKoiPackDes());

        //add breeder
        List<Breeder> breederList = new ArrayList<>();

        for(BreederRequest breederRequest : koiPackRequest.getBreederRequestList()){
            Breeder breeder = new Breeder();
            breeder.setBreederName(breederRequest.getBreederName());
            breeder.setBreederPhone(breederRequest.getBreederPhone());
            breederList.add(breeder);
        }
        koiPack.setBreeders(breederList);
        //add variety
        List<Variety> varieties= new ArrayList<>();
        for(VarietyRequest varietyRequest : koiPackRequest.getVarietyRequestList()){
            Variety variety = new Variety();
            variety.setVarietyDes(varietyRequest.getVarietyDes());
            variety.setVarietyName(varietyRequest.getVarietyName());
            varieties.add(variety);
        }
        koiPack.setVarieties(varieties);

        return koiPackRepository.save(koiPack);

    }
    //find koi pack by id
    public KoiPack getKoiPackById(long id){
        KoiPack koiPack = koiPackRepository.findById(id);
        if(koiPack == null) throw new BadRequestException("Koi pack not found!");
        return koiPack;
    }

    //Find koi pack by name
    public List<KoiPack> findByName(String name) {
        List<KoiPack> koiPackList = koiPackRepository.findByKoiPackName(name);
        if (koiPackList.isEmpty()) {
            throw new BadRequestException("Koi pack not found!");
        }
        return koiPackList;
    }

    //Sorted by price Asc
    public List<KoiPack> sortedByPriceAsc(){
        return koiPackRepository.findAllByOrderByPriceAsc();
    }

    //Sorted by price Desc
    public List<KoiPack> sortedByPriceDesc(){
        return koiPackRepository.findAllByOrderByPriceDesc();
    }

    //Find by breeder
    public List<KoiPack> findKoiPacksByBreederName(String breederName) {
        List<KoiPack> koiPacks = koiPackRepository.findAll();
        List<KoiPack> result = new ArrayList<>();

        for (KoiPack koiPack : koiPacks) {
            for (Breeder breeder : koiPack.getBreeders()) {
                if (breeder.getBreederName().equalsIgnoreCase(breederName)) {
                    result.add(koiPack);
                    break;
                }
            }
        }
        return result;
    }

    //Find by variety
    public List<KoiPack> findKoiPacksByVarietyName(String varietyName){
        List<KoiPack> koiPackList = koiPackRepository.findAll();
        List<KoiPack> result = new ArrayList<>();
        for(KoiPack koiPack : koiPackList){
            for(Variety variety : koiPack.getVarieties()){
                if(variety.getVarietyName().equalsIgnoreCase(varietyName)){
                    result.add(koiPack);
                    //chỉ cần giống là lấy ra lưu cá ko can xét thêm variety sau
                    break;
                }
            }
        }
        return result;
    }

    //Delete koipack by Id
    public KoiPack deleteById(long id){
        KoiPack koiPack = getKoiPackById(id);
        koiPack.setDeleted(true);
        return koiPackRepository.save(koiPack);
    }
}








