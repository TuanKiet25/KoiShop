package com.example.demo.model.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class KoiPackRequest {
    private  String koiPackName;

    private String koiPackGender;

    private LocalDate koiPackBorn;

    private String koiPackSize;

    private int koiPackQuantity;

    private String koiPackStatus;

    private float price;

    private String koiPackDes;

    List<BreederRequest> breederRequestList;

    List<VarietyRequest> varietyRequestList;

    List<String> mediaRequestUrlList;

}
