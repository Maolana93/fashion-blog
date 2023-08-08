package com.week.nine.FashionBlogAPI.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogUserSignUpRequest {
    @Email(message = "Please kindly provide your email")
    @NotBlank(message = "provide valid email")
    private String email;
    private String name;
    @NotBlank(message = "provide valid password")
    private String password;

}
