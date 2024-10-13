package com.example.demo.repository;

import com.example.demo.Entity.Variety;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VarietyRepository extends JpaRepository<Variety, Long> {
    Variety findByVarietyName(String varietyName);
}
