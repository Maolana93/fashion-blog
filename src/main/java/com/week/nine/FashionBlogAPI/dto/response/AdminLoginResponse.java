package com.week.nine.FashionBlogAPI.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminLoginResponse {
    private Long id;
    private String email;
    private String message;
}
