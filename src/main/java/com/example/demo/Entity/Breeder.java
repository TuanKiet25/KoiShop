package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "breeder")
@Getter
@Setter
public class Breeder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "breeder_name")
    private String breederName;

    @Column(name = "breeder_phone")
    private String breederPhone;

    @Column(name = "breeder_address")
    private String breederAdd;

    @OneToMany(mappedBy = "breeder")
    private Set<Koi> kois = new HashSet<>();

    @ManyToMany(mappedBy = "breeders")
    @JsonIgnore
    private List<KoiPack> koiPacks = new ArrayList<>();


}
