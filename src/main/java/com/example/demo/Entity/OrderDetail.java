package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orderDetail")
@Getter
@Setter
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "orderDetail_quantity")
    private int orderDetailQuantity;
    
    @Column(name = "price")
    private float price;

    @Column(name = "orderDetail_status")
    private String orderDetailStatus;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "koi_id")
    private Koi koi;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "koiPack_id")
    private KoiPack koiPack;

   @ManyToOne
    @JoinColumn(name = "order_id")
    private KoiOrder koiOrder;

}
