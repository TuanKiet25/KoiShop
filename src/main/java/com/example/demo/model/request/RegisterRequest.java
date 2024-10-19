package com.example.demo.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    String email;
    @Size(min = 6, message = "Password must be at least 6 character!!!")
    String password;
    String fullName;
//    @Pattern(regexp = "(84|0[3|5|7|8|9])+(\\d{8})", message = "Invalid phone number!!!")
    @Column(unique = true)
    String phone;
}
