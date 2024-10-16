package com.example.demo.Service;

import com.example.demo.Entity.Payment;
import com.example.demo.model.request.PaymentRequest;
import com.example.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    //create
    public Payment createPayment(PaymentRequest paymentRequest)
    {
        Payment payment = new Payment();
        payment.setAmount(paymentRequest.getAmount());
        payment.setPaymentDate(paymentRequest.getDate());
        payment.setPaymentStatus(paymentRequest.getStatus());
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments()
    {
        return paymentRepository.findAll();
    }

}
