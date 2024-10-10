package com.example.demo.Service;

import com.example.demo.Entity.Variety;
import com.example.demo.repository.VarietyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VarietyService {
    @Autowired
    private VarietyRepository varietyRepository;

    //create
    public Variety createNewVariety(Variety variety) {
        return varietyRepository.save(variety);
    }

    //read
    public List<Variety> getAllVariety() {
        return varietyRepository.findAll();
    }

    //delete
    public Variety deleteVariety(long id)
    {
        Variety deleteVariety = varietyRepository.findVarietiesById(id);
        if (deleteVariety == null) throw new EntityNotFoundException("Variety Not Found");
        deleteVariety.setDeleted(true);
        return varietyRepository.save(deleteVariety);
    }

    //update
    public Variety updateVariety(long id, Variety variety)
    {
        Variety updateVariety = varietyRepository.findVarietiesById(id);
        if (updateVariety == null) throw new EntityNotFoundException("Variety Not Found");
        updateVariety.setName(variety.getName());
        return varietyRepository.save(updateVariety);
    }

    //select by variety name
    public Variety findVarietyByName(String name)
    {
        return varietyRepository.findVarietiesByName(name);
    }
}
