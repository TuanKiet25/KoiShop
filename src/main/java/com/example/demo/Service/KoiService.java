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
        if (breeder == null){
            breeder = breederRepository.save(new Breeder());
        }

        Variety variety = varietyRepository.findByVarietyName(koiRequest.getVarietyName());
        if (variety == null){
            variety = varietyRepository.save(new Variety());
        }

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
        List<Koi> koiList = checkDelete(kois);
        List<KoiRequest> koiRequests = setup(koiList);
        return koiRequests;
    }


    //update  koi
    public Koi update(long koiId, KoiRequest koiRequest){
        Breeder breeder = breederRepository.findByBreederName(koiRequest.getBreederName());
        if (breeder == null){
            breeder = breederRepository.save(new Breeder());
        }

        Variety variety = varietyRepository.findByVarietyName(koiRequest.getVarietyName());
        if (variety == null){
            variety = varietyRepository.save(new Variety());
        }
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
    public List<KoiRequest> findByName(String koiName){
        List<Koi> koiList = koiRepository.findByKoiName(koiName);
        List<Koi> kois = checkDelete(koiList);
        if(kois.isEmpty()) throw new BadRequestException("Koi not found!");
        List<KoiRequest> koiRequestList = setup(kois);

        return koiRequestList;
    }

    //findKoiByBreeder
    public List<KoiRequest> findByBreeder(String breederName){
        Breeder breeder = breederRepository.findByBreederName(breederName);
        if(breeder == null) throw new BadRequestException("Breeder does not exist!");
        List<Koi> koiBreederList =koiRepository.findByBreeder(breeder);
        List<Koi> koiList = checkDelete(koiBreederList);
        List<KoiRequest> koiRequests = setup(koiList);
        return koiRequests;
    }

    //Sorted by price Asc
    public List<KoiRequest> sortedByPriceAsc(){
        List<Koi> koiList = koiRepository.findAllByOrderByPriceAsc();
        List<Koi> kois = checkDelete(koiList);
        List<KoiRequest> koiRequests = setup(kois);
        return koiRequests;
    }

    //Sorted by price Desc
    public List<KoiRequest> sortedByPriceDesc(){
        List<Koi> koiList = koiRepository.findAllByOrderByPriceDesc();
        List<Koi> kois = checkDelete(koiList);
        List<KoiRequest> koiRequests = setup(kois);
        return koiRequests;
    }

    //Find by Variety
    public List<KoiRequest> findByVariety(String varietyName) {
        Variety variety = varietyRepository.findByVarietyName(varietyName);
        if (variety == null) throw new BadRequestException("Variety does not exist!");
        List<Koi> koiListVariety = koiRepository.findByVariety(variety);
        List<Koi> koiList = checkDelete(koiListVariety);
        List<KoiRequest> koiRequests = setup(koiList);
        return koiRequests;
    }

    //Find by id
    public Koi getKoiById(long id){
        Koi koi = koiRepository.findById(id);
        if(koi == null) throw new EntityNotFoundException();
        return koi;
    }
    //checkDelete
    public List<Koi> checkDelete(List<Koi> koiList){
        List<Koi> kois = new ArrayList<>();
        for(Koi koi  : koiList) {
            if (koi.isDeleted() == false) {
                kois.add(koi);
            }
        }
        return kois;
    }
    //set up koi request
    public List<KoiRequest> setup(List<Koi> kois){
        List<KoiRequest> koiRequestList = new ArrayList<>();
        for (Koi koi : kois) {
            KoiRequest koiRequest = new KoiRequest();
            koiRequest.setId(koi.getId());
            koiRequest.setKoiName(koi.getKoiName());
            koiRequest.setKoiSize(koi.getKoiSize());
            koiRequest.setKoiBorn(koi.getKoiBorn());
            koiRequest.setKoiGender(koi.getKoiGender());
            koiRequest.setPrice(koi.getPrice());
            koiRequest.setKoiDes(koi.getKoiDes());
            koiRequest.setKoiPrize(koi.getKoiPrize());
            koiRequest.setKoiStatus(koi.getKoiStatus());
            koiRequest.setVarietyName(koi.getVariety().getVarietyName());
            koiRequest.setBreederName(koi.getBreeder().getBreederName());
            List<MediaRequest> mediaRequests = new ArrayList<>();
            for (Media media : koi.getMediaList()) {
                MediaRequest mediaRequest = new MediaRequest();
                mediaRequest.setUrl(media.getUrl());
                mediaRequests.add(mediaRequest);
            }
            koiRequest.setMediaRequestList(mediaRequests);
            koiRequestList.add(koiRequest);
        }
        return koiRequestList;
    }
}
