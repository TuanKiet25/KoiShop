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
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ConsignmentService
{
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
        if(account.getRole().equals(Role.CUSTOMER))
        {
            consignment.setConsignmentCreateDate(LocalDate.now());
            consignment.setConsignmentStatus(ConsignmentStatus.REQUESTED);
            consignment.setConsignmentPrice(0);
            consignment.setConsignmentDes(null);
            consignment.setConsignmentFee(0);
            consignment.setConsignmentSignDate(null);
            consignment.setAccount(accountRepository.findAccountById(account.getId()));
            consignment.setKoi(koiService.createKoi(koiRequest));
            consignment.setPaymentMethods(null);
        }
        else{
            throw new BadRequestException("Invalid role");
        }
        return consignmentRepository.save(consignment);
    }
}
