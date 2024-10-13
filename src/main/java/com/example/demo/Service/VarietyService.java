package com.example.demo.Service;

import com.example.demo.Entity.Variety;
import com.example.demo.model.request.VarietyRequest;
import com.example.demo.repository.VarietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VarietyService {
    @Autowired
    VarietyRepository varietyRepository;

    public Variety crateVariety(VarietyRequest varietyRequest){
        Variety variety = new Variety();
        variety.setVarietyName(varietyRequest.getVarietyName());
        variety.setVarietyDes(varietyRequest.getVarietyDes());
        return varietyRepository.save(variety);
    }
}
