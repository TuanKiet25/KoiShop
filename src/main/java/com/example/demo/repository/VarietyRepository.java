package com.example.demo.repository;

import com.example.demo.Entity.Variety;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VarietyRepository extends JpaRepository<Variety, Long> {
    Variety findByVarietyName(String varietyName);

    List<Variety> findByVarietyNameContaining(String varietyName);
}
