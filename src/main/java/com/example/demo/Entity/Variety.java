package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "variety")
@Getter
@Setter
public class Variety {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "variety_name")
    private String varietyName;

    @OneToMany(mappedBy = "variety")
    private Set<Koi> kois = new HashSet<>();

    @OneToMany(mappedBy = "variety")
    private Set<KoiPackVariety> koiPackVarieties = new HashSet<>();
}
