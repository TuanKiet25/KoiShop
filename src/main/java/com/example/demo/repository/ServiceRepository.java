package com.example.demo.repository;

import com.example.demo.Entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.Service;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long>{
    Service findServiceById(long id);
}
