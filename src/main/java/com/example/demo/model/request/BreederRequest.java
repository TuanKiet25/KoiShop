package com.example.demo.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class BreederRequest {
   
    private String breederName;

//    @Pattern(regexp = "(84|0[3|5|7|8|9])+(\\d{8})", message = "Invalid phone number!!!")
    private String breederPhone;


    private String breederAdd;
}
