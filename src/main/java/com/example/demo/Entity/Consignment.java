package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "consignment")
@Getter
@Setter
public class Consignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "consignment_date")
    private LocalDate consignmentDate;

    @Column(name = "consignment_status")
    private String consignmentStatus;

    @Column(name = "consignment_price")
    private float consignmentPrice;

    @Column(name = "consignment_description")
    private String consignmentDes;

    @Column(name = "consignment_Fee")
    private float consignmentFee;


    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "koi_id")
    private Koi koi;

    @OneToMany(mappedBy = "consignment")
    private Set<PaymentCosign> paymentCosigns = new HashSet<>();

}
