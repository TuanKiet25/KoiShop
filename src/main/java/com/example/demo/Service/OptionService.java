package com.example.demo.Service;


import com.example.demo.Entity.ServiceOption;
import com.example.demo.Entity.Store;
import com.example.demo.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OptionService {
    @Autowired
    OptionRepository optionRepository;

    public ServiceOption createNewOption(ServiceOption option){
        optionRepository.save(option);
        return option;
    }
    public List<ServiceOption> getAllOption(){
        return optionRepository.findAll();
    }
    public ServiceOption deleteOption(long id){
        ServiceOption option= optionRepository.findOptionById(id);
        option.setDeleted(true);
        return optionRepository.save(option);
    }
}
