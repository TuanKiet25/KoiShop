package com.example.demo.repository;

import com.example.demo.Entity.Breeder;
import com.example.demo.Entity.Koi;
import com.example.demo.Entity.KoiPack;
import com.example.demo.Entity.Variety;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KoiPackRepository extends JpaRepository<KoiPack, Long> {
    List<KoiPack> findByKoiPackName(String koiPackName);
    KoiPack findById(long id);
    List<KoiPack> findAllByOrderByPriceAsc();
    List<KoiPack> findAllByOrderByPriceDesc();

}
