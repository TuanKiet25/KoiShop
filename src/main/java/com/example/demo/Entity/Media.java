package com.example.demo.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Media {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private String url;

    boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "koi_pack_id")
    @JsonIgnore
    private KoiPack koiPack;

}