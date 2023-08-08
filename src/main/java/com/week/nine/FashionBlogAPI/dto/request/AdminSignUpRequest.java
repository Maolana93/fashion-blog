package com.week.nine.FashionBlogAPI.dto.request;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminSignUpRequest {
    @Email(message = "Please kindly provide your email")
    @NotBlank(message = "provide valid email")
    private String email;
    private String name;
    @NotBlank(message = "provide valid password")
    private String password;
}

