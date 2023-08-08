package com.week.nine.FashionBlogAPI.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Builder
public class AdminLoginRequest {
    @Email(message = "Please kindly provide your email")
    @NotBlank(message = "provide valid email")
    private String email;
    @NotBlank(message = "provide valid password")
    private String password;
}
