package com.example.demo.model.request;

import lombok.Data;

@Data
public class RegisterRequest {
    String email;
    String password;
    String fullName;
    String phone;
}
