package com.example.demo.model.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @Pattern(regexp = "(84|0[3|5|7|8|9])+(\\d{8})", message = "Invalid phone number!!!")
    String phone;
    @Size(min = 6, message = "Password must be at least 6 character!!!")
    String password;
}
