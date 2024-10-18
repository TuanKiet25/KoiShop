package com.example.demo.model.request;


import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class KoiRequest {

    
    private String koiName;

    private String koiSize;

    private LocalDate koiBorn;

    private String koiGender;

    private float price;

    private String koiDes;

    private String koiPrize;

    private String koiStatus;

    private String breederName;

    private String varietyName;

    private List<MediaRequest> mediaRequestList;
}
