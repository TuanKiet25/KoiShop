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
@Table(name = "variety")
@Getter
@Setter
public class Variety {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "variety_name")
    private String varietyName;

    @Column(name = "variety_Description")
    private String varietyDes;

    private boolean isDeleted=false;

    @OneToMany(mappedBy = "variety")
    private Set<Koi> kois = new HashSet<>();

//    @OneToMany(mappedBy = "variety")
//    private Set<KoiPackVariety> koiPackVarieties = new HashSet<>();

    @ManyToMany(mappedBy = "varieties")
    @JsonIgnore
    private List<KoiPack> koiPacks = new ArrayList<>();
}
