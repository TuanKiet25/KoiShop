package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class KoiPack
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String koiPackName;

    private float koiPackPrice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "koiPack", cascade = CascadeType.ALL)
    private List<Media> mediaList;

    @ManyToMany
    @JoinTable(
            name = "koi_pack_varieties"
            , joinColumns = @JoinColumn(name = "variety_id")
            , inverseJoinColumns = @JoinColumn (name = "koi_pack_id")
    )
    Set<Variety> varietyName   ;


    @ManyToMany
    @JoinTable(
            name = "koi_pack_breeders"
            , joinColumns = @JoinColumn(name = "breeder_id")
            , inverseJoinColumns = @JoinColumn (name = "koi_pack_id")
    )
    Set<Breeder> breedersName;
}
