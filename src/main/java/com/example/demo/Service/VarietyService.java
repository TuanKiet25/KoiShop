package com.example.demo.Service;

import com.example.demo.Entity.Variety;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.request.VarietyRequest;
import com.example.demo.repository.VarietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VarietyService {
    @Autowired
    VarietyRepository varietyRepository;

    public Variety crateVariety(VarietyRequest varietyRequest){
        Variety variety = new Variety();
        List<Variety> varieties = varietyRepository.findAll();
        variety.setVarietyName(varietyRequest.getVarietyName());
        for(Variety variety1 : varieties){
            if(variety1.getVarietyName().equalsIgnoreCase(variety.getVarietyName())){
                throw new BadRequestException("Variety already exist!!!");
            }
        }
        variety.setVarietyDes(varietyRequest.getVarietyDes());
        return varietyRepository.save(variety);
    }
}
