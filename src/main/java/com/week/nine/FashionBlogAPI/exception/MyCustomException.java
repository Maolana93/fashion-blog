package com.week.nine.FashionBlogAPI.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Getter
public class MyCustomException extends RuntimeException{
    private String message;

    private HttpStatus status;
    private final LocalDateTime time = LocalDateTime.now();

    public MyCustomException(String message) {
        this.message = message;
    }

    public MyCustomException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
