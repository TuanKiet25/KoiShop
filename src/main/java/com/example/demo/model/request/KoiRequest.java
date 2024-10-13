package com.example.demo.model.request;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class KoiRequest {

    private String koiName;

    private String koiSize;

    private LocalDate koiBorn;

    private String koiGender;

    private double price;

    private String koiDes;

    private String koiPrize;

    private String koiStatus;

    private String breederName;

    private String varietyName;
}
