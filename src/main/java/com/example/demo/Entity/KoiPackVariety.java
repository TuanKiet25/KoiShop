package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "koiPack_variety")
@Getter
@Setter
public class KoiPackVariety {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "koiPack_id")
    private KoiPack koiPack;

    @ManyToOne
    @JoinColumn(name = "variety_id")
    private Variety variety;
}
