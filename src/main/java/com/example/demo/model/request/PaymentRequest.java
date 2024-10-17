package com.example.demo.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class PaymentRequest
{
    private float amount;
    private Date date;
    private String status;
}
