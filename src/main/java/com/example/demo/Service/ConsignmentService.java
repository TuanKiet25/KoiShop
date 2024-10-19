package com.example.demo.Service;

import com.example.demo.Entity.Account;
import com.example.demo.Entity.Consignment;
import com.example.demo.Entity.enums.ConsignmentStatus;
import com.example.demo.Entity.enums.Role;
import com.example.demo.model.request.KoiRequest;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.ConsignmentRepository;
import com.example.demo.repository.KoiRepository;
import jakarta.validation.constraints.Null;
import javassist.NotFoundException;
import com.example.demo.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConsignmentService {
    @Autowired
    private ConsignmentRepository consignmentRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private KoiService koiService;

    @Autowired
    private AccountService accountService;

//    @Autowired
//    public ConsignmentService(KoiService koiService, AccountRepository accountRepository, ConsignmentRepository consignmentRepository) {
//        this.koiService = koiService;
//        this.accountRepository = accountRepository;
//        this.consignmentRepository = consignmentRepository;
//    }

    //customer create cosign
    public Consignment createConsignment(KoiRequest koiRequest) throws BadRequestException {
        Consignment consignment = new Consignment();
        Account account = accountService.getCurrentAccount();
        if (!account.getRole().equals(Role.CUSTOMER)) {
            throw new BadRequestException("Only customer can make consignment request");
        }

        consignment.setConsignmentCreateDate(LocalDate.now());
        consignment.setConsignmentStatus(ConsignmentStatus.REQUESTED);
        consignment.setConsignmentPrice(0);
        consignment.setConsignmentDes(null);
        consignment.setConsignmentFee(0);
        consignment.setConsignmentSignDate(null);
        consignment.setAccount(accountRepository.findAccountById(account.getId()));
        consignment.setKoi(koiService.createKoi(koiRequest));
        consignment.setPaymentMethods(null);

        return consignmentRepository.save(consignment);
    }

    public Consignment changeConsignmentToWaiting(Long consignmentId) //throws NotFoundException
    {
//        Account account = accountService.getCurrentAccount();
        Consignment consignment = consignmentRepository.findById(consignmentId).get();
//        if (consignment == null) {
//            throw new NotFoundException("Consignment not found");
//        }
//        if (!account.getRole().equals(Role.MANAGER)) {
//            throw new BadRequestException("Only customer can make consignment request");
//        }
        consignment.setConsignmentStatus(ConsignmentStatus.WAITING);
        return consignmentRepository.save(consignment);
    }

    public List<Consignment> getAllConsignments()
    {
        List<Consignment> consignments = consignmentRepository.findAll();
        return consignments;
    }

}
