package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
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

    @OneToMany(mappedBy = "breeder")
    private Set<KoiPackBreeder> koiPackBreeders = new HashSet<>();

}
