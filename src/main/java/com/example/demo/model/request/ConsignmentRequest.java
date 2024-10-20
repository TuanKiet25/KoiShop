package com.example.demo.model.request;

import lombok.Data;

@Data
public class ConsignmentRequest
{
    private float consignmentSuggestionPrice;

    private String consignmentDescription;

    private float consignmentConfirmedPrice;
}
