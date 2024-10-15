package com.example.demo.repository;

import com.example.demo.Entity.KoiPack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KoiPackRepository extends JpaRepository<KoiPack, Long> {
    KoiPack findByKoiPackName(String koiPackName);
    KoiPack findById(long id);
}
