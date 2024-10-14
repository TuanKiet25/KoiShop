package com.example.demo.repository;

import com.example.demo.Entity.Breeder;
import com.example.demo.Entity.Koi;
import com.example.demo.Entity.Variety;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KoiRepository  extends JpaRepository<Koi, Long> {
    //customize
    Koi findById(long id);
    Koi findByKoiName(String koiName);
    List<Koi> findAllByOrderByPriceAsc();
    List<Koi> findAllByOrderByPriceDesc();
    List<Koi> findByBreeder(Breeder breeder);
    List<Koi> findByVariety(Variety variety);
}
