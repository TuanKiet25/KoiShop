package com.example.demo.repository;

import com.example.demo.Entity.Koi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KoiRepository  extends JpaRepository<Koi, Long> {
    //customize
    Koi findKoiById(long id);
}
