package com.example.demo.Service;

import com.example.demo.Entity.Account;
import com.example.demo.Entity.Consignment;
import com.example.demo.Entity.enums.ConsignmentStatus;
import com.example.demo.Entity.enums.Role;
import com.example.demo.model.request.ConsignmentRequest;
import com.example.demo.model.request.KoiRequest;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.ConsignmentRepository;
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
        consignment.setConsignmentSuggestionPrice(0);
        consignment.setConsignmentDes(null);
        consignment.setConsignmentConfirmedPrice(0);
        consignment.setConsignmentSignDate(null);
        consignment.setAccount(accountRepository.findAccountById(account.getId()));
        consignment.setKoi(koiService.createKoi(koiRequest));
        consignment.setPaymentMethods(null);

        return consignmentRepository.save(consignment);
    }

    public Consignment waitingConsignment(Long consignmentId) //throws NotFoundException
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

    public Consignment checkConsignment(Long consignmentId,ConsignmentRequest consignmentRequest)
    {
        Consignment consignment = consignmentRepository.findById(consignmentId).get();
        consignment.setConsignmentStatus(ConsignmentStatus.REQUESTED);
        consignment.setConsignmentDes(consignmentRequest.getConsignmentDescription());
        return consignmentRepository.save(consignment);
    }

    public Consignment selectConsignmentById(Long consignmentId)
    {
        return consignmentRepository.findById(consignmentId).get();
    }

    public Consignment cancelConsign (Long consignmentId)
    {
        Consignment consignment = consignmentRepository.findById(consignmentId).get();
        consignment.setConsignmentStatus(ConsignmentStatus.CANCELED);
        return consignmentRepository.save(consignment);
    }

    public Consignment approveConsignment (Long consignmentId, ConsignmentRequest consignmentRequest)
    {
        Consignment consignment = consignmentRepository.findById(consignmentId).get();
        consignment.setConsignmentStatus(ConsignmentStatus.APPROVED);
        consignment.setConsignmentSuggestionPrice(consignmentRequest.getConsignmentSuggestionPrice());
        return consignmentRepository.save(consignment);
    }

    public Consignment consignedConsignment(Long consignmentId, ConsignmentRequest consignmentRequest)
    {
        Consignment consignment = consignmentRepository.findById(consignmentId).get();
        consignment.setConsignmentStatus(ConsignmentStatus.CONSIGNED);
        consignment.setConsignmentConfirmedPrice(consignmentRequest.getConsignmentConfirmedPrice());
        return consignmentRepository.save(consignment);
    }

    public Consignment paidConsignment(Long consignmentId)
    {
        Consignment consignment = consignmentRepository.findById(consignmentId).get();
        consignment.setConsignmentStatus(ConsignmentStatus.PAID);
        return consignmentRepository.save(consignment);
    }

}
