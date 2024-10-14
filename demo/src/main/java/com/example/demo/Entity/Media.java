package com.example.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Media {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;


    private String url;

    boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "koi_pack_id")
    private KoiPack koiPack;

}
