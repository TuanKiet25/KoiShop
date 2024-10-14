package com.example.demo.repository;

import com.example.demo.Entity.KoiPack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KoiPackRespository extends JpaRepository<KoiPack, Long>
{
    KoiPack findByKoiPackName(String name);
}
