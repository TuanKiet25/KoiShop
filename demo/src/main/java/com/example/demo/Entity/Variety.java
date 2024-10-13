package com.example.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Variety
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "name can not be blank")
    private String name;

    boolean isDeleted = false;

    @ManyToMany(mappedBy = "varietyName")
    Set<KoiPack> KoiPackof;
}
