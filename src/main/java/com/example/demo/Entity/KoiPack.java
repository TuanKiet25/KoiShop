package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "koiPack_name")
    private String koiPackName;

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
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "KoiPackBreeder", joinColumns = @JoinColumn(name = "koiPackId"), inverseJoinColumns = @JoinColumn(name = "breederId"))
    private List<Breeder> breeders = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "KoiPackVariety", joinColumns = @JoinColumn(name = "koiPackId"), inverseJoinColumns = @JoinColumn(name = "varietyId"))
    private List<Variety> varieties = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "koiPack", cascade = CascadeType.ALL)
    private List<Media> mediaList;


}
