package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "paymentMethod")
@Getter
@Setter
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "paymentMethod_name")
    private String paymentMethodName;

    @Column(name = "paymentMethod_status")
    private String paymentMethodStatus;

    @Column(name = "transactionFee")
    private float transactionFee;

    @OneToMany(mappedBy = "paymentMethod")
    private Set<Payment> payments = new HashSet<>();

    @OneToMany(mappedBy = "paymentMethod")
    private Set<PaymentCosign> paymentCosigns = new HashSet<>();

}
