package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "koiOrder")
@Getter
@Setter
public class KoiOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "total_amount")
    private float totalAmount;


    @OneToMany(mappedBy = "koiOrder", cascade = CascadeType.ALL)//luc luu order no se giup minh luu luon orderdetail
    @JsonIgnore
    private List<OrderDetail> orderDetails = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "koi_id")
    private Koi koi;

   @OneToMany(mappedBy = "koiOrder")
    private Set<Payment> payments = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
