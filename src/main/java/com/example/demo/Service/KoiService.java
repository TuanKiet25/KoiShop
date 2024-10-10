package com.example.demo.Service;

import com.example.demo.Entity.Koi;
import com.example.demo.repository.KoiRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KoiService  {
    @Autowired
    KoiRepository koiRepository;
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
        oldkoi.setKoiPrice(koi.getKoiPrice());
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

    public Koi getKoiById(long id){
        Koi koi = koiRepository.findKoiById(id);
        if(koi == null) throw new EntityNotFoundException();
        return koi;
    }
}
