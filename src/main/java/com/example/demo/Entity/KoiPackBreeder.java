package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "koiPack_breeder")
@Getter
@Setter
public class KoiPackBreeder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "koiPack_id")
    private KoiPack koiPack;

    @ManyToOne
    @JoinColumn(name = "breeder_id")
    private Breeder breeder;

}
