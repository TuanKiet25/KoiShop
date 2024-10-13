package com.example.demo.Service;

import com.example.demo.Entity.KoiPack;
import com.example.demo.Entity.Variety;
import com.example.demo.repository.KoiPackRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KoiPackService {
    @Autowired
    KoiPackRespository koiPackRespository;

    //create
    public KoiPack createKoiPack(KoiPack KoiPack)
    {
        return koiPackRespository.save(KoiPack);
    }

    //getall
    public List<KoiPack> getAllKoiPack()
    {
        return koiPackRespository.findAll();
    }

    //deleted

}
