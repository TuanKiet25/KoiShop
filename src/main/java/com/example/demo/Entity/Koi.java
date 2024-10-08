package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "koiFish")
@Getter
@Setter
public class Koi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "koi_name")
    private String koiName;

    @Column(name =  "koi_size")
    private String koiSize;

    @Column(name = "koi_born")
    private LocalDate koiBorn;

    @Column(name = "koi_gender")
    private String koiGender;

    @Column(name = "koi_price")
    private float koiPrice;

    @Column(name = "Koi_description")
    private String koiDes;

    @Column(name = "koi_prize")
    private String koiPrize;

    @Column(name = "koi_status")
    private String koiStatus;

    @ManyToOne
    @JoinColumn(name = "breeder_id")
    private Breeder breeder;

    @ManyToOne
    @JoinColumn(name = "variety_id")
    private Variety variety;

    private  boolean isDeleted = false;

}
