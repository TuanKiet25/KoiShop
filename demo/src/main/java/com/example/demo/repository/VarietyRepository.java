package com.example.demo.repository;

import com.example.demo.Entity.Variety;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VarietyRepository extends JpaRepository<Variety, Long> {
    Variety findVarietiesById(Long id);
}
