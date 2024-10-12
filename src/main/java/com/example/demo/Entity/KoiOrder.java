package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "koiOrder")
@Getter
@Setter
public class KoiOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "total_amount")
    private float totalAmount;


    @OneToMany(mappedBy = "koiOrder")
    private Set<OrderDetail> orderDetails = new HashSet<>();

   @OneToMany(mappedBy = "koiOrder")
    private Set<Payment> payments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
