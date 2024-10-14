package com.example.demo.Service;

import com.example.demo.Entity.Breeder;
import com.example.demo.repository.BreederRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreederService {
    @Autowired
    private BreederRepository breederRepository;

    //get all breeder
    public List<Breeder> getAllBreeders()
    {
        return  breederRepository.findAll();
    }

    //get breeder by name
    public Breeder getBreederNames(String breederName)
    {
        return breederRepository.findBreederByBreederName(breederName);
    }
}
