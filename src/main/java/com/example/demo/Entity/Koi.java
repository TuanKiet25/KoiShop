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
    private float price;

    @Column(name = "Koi_description")
    private String koiDes;

    @Column(name = "koi_prize")
    private String koiPrize;

    @Column(name = "koi_status")
    private String koiStatus;

    @ManyToOne
    @JoinColumn(name = "breeder_id")
    @JsonIgnore
    private Breeder breeder;



    @ManyToOne
    @JoinColumn(name = "variety_id")
    @JsonIgnore
    private Variety variety;

    @OneToMany(mappedBy = "koi", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Media> mediaList = new ArrayList<>();

   @OneToMany(mappedBy = "koi", cascade = CascadeType.ALL)
   private Set<Consignment> consignments = new HashSet<>();

    private  boolean isDeleted = false;

}
