package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "Media")
    private Media media;

    @ManyToMany
    Set<Variety> varieties;

    @ManyToMany
    Set<Breeder> breeders;
}
