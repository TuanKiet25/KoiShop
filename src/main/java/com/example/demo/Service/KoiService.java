package com.example.demo.Service;

import com.example.demo.Entity.Breeder;
import com.example.demo.Entity.Koi;
import com.example.demo.Entity.Media;
import com.example.demo.Entity.Variety;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.request.KoiRequest;
import com.example.demo.model.request.MediaRequest;
import com.example.demo.repository.BreederRepository;
import com.example.demo.repository.KoiRepository;
import com.example.demo.repository.Mediarepository;
import com.example.demo.repository.VarietyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KoiService  {
    @Autowired
    KoiRepository koiRepository;
    @Autowired
    BreederRepository breederRepository;
    @Autowired
    VarietyRepository varietyRepository;
    @Autowired
    Mediarepository mediarepository;

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
        List<Media> mediaList =  new ArrayList<>();
        for(MediaRequest mediaRequest: koiRequest.getMediaRequestList()){
            Media media = new Media();
            media.setUrl(mediaRequest.getUrl());
            mediaList.add(media);
        }
        newKoi.setMediaList(mediaList);

        return koiRepository.save(newKoi);
    }


    //read koi
    public List<KoiRequest> getAllKoi(){
    List<Koi> kois= koiRepository.findAll();
    List<Koi> koiList = new ArrayList<>();
    for(Koi koi  : kois) {
        if (koi.isDeleted() == false) {
            koiList.add(koi);
        }
    }
    List<KoiRequest> koiRequests = new ArrayList<>();
    for(Koi koi1 : koiList) {
        KoiRequest koiRequest = new KoiRequest();
        koiRequest.setKoiName(koi1.getKoiName());
        koiRequest.setKoiSize(koi1.getKoiSize());
        koiRequest.setKoiBorn(koi1.getKoiBorn());
        koiRequest.setKoiGender(koi1.getKoiGender());
        koiRequest.setPrice(koi1.getPrice());
        koiRequest.setKoiDes(koi1.getKoiDes());
        koiRequest.setKoiPrize(koi1.getKoiPrize());
        koiRequest.setKoiStatus(koi1.getKoiStatus());
        koiRequest.setVarietyName(koi1.getVariety().getVarietyName());
        koiRequest.setBreederName(koi1.getBreeder().getBreederName());
        koiRequests.add(koiRequest);
        List<MediaRequest> mediaRequests = new ArrayList<>();
        for (Media media : koi1.getMediaList()) {
            MediaRequest mediaRequest = new MediaRequest();
            mediaRequest.setUrl(media.getUrl());
            mediaRequests.add(mediaRequest);
        }
        koiRequest.setMediaRequestList(mediaRequests);
        koiRequests.add(koiRequest);
    }

    return koiRequests;
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
        List<Media> mediaList =  new ArrayList<>();
        for(MediaRequest mediaRequest: koiRequest.getMediaRequestList()){
            Media media = new Media();
            media.setUrl(mediaRequest.getUrl());
            mediaList.add(media);
        }
        oldkoi.setMediaList(mediaList);
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
        List<Koi> kois = new ArrayList<>();
        for(Koi koi : koiList){
            if(koi.isDeleted()== false){
                kois.add(koi);
            }
        }
        if(kois.isEmpty()) throw new BadRequestException("Koi not found!");
        return kois;
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
