package com.example.demo.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class KoiPackRequest
{
    String name;

    float price;

    List<MultipartFile> mediaList;
}
