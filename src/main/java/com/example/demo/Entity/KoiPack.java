package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "koi_pack")
@Getter
@Setter
public class KoiPack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "koiPack_gender")
    private String koiPackGender;

    @Column(name = "koiPack_born")
    private LocalDate koiPackBorn;

    @Column(name = "koiPack_size")
    private String koiPackSize;

    @Column(name = "koiPack_quantity")
    private int koiPackQuantity;

    @Column(name = "koiPack_status")
    private String koiPackStatus;

    @Column(name = "koiPack_price")
    private float koiPackPrice;

    @Column(name = "koiPack_description")
    private String koiPackDes;

    @OneToMany(mappedBy = "koiPack")
    private Set<KoiPackBreeder> koiPackBreeders = new HashSet<>();

    @OneToMany(mappedBy = "koiPack")
    private Set<KoiPackVariety> koiPackVarieties = new HashSet<>();




}
