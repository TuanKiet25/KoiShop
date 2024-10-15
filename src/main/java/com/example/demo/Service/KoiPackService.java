package com.example.demo.Service;

import com.example.demo.Entity.Breeder;
import com.example.demo.Entity.KoiPack;
import com.example.demo.model.request.KoiPackRequest;
import com.example.demo.repository.BreederRepository;
import com.example.demo.repository.KoiPackRepository;
import com.example.demo.repository.Mediarepository;
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

    @Autowired
    Mediarepository mediaRepository;
    //create
    public KoiPack createKoiPack(KoiPackRequest KoiPackRequest)
    {
        KoiPack newKoiPack = new KoiPack();
        newKoiPack.setKoiPackName(KoiPackRequest.getKoiPackName());
        newKoiPack.setKoiPackGender(KoiPackRequest.getKoiPackGender());
        newKoiPack.setKoiPackBorn(KoiPackRequest.getKoiPackBorn());
        newKoiPack.setKoiPackPrice(KoiPackRequest.getKoiPackPrice());
        newKoiPack.setKoiPackQuantity(KoiPackRequest.getKoiPackQuantity());
        newKoiPack.setKoiPackSize(KoiPackRequest.getKoiPackSize());
        newKoiPack.setKoiPackDes(KoiPackRequest.getKoiPackDes());
        newKoiPack.setKoiPackStatus(KoiPackRequest.getKoiPackStatus());


        return koiPackRepository.save(newKoiPack);
    }

    //getall
    public List<KoiPack> getAllKoiPack()
    {
        return koiPackRepository.findAll();
    }

    //deleted

}
