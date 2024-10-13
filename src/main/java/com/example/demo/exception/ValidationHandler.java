package com.example.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationHandler {
        @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidation(MethodArgumentNotValidException exception){
        String message ="";
       //Luu các thuộc tính bị lỗi cứ mỗi thuộc tính bị lỗi thì sẽ thống kê và resp về cho fe
    for(FieldError fieldError : exception.getBindingResult().getFieldErrors()){
        // cứ một lỗi thì sẽ trả về mess tương ứng
        message += fieldError.getDefaultMessage();
    }
    //trả lỗi về
    return ResponseEntity.ok(message);
    }
}
