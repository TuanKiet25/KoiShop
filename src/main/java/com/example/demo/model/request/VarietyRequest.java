package com.example.demo.model.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class VarietyRequest {
    private String varietyName;

    private String varietyDes;
}
