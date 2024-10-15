package com.example.demo.repository;

import com.example.demo.Entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Mediarepository extends JpaRepository<Media, Long>
{
    public Media findByUrl(String url);
}
