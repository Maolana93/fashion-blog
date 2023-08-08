package com.week.nine.FashionBlogAPI.dto.response;

import com.week.nine.FashionBlogAPI.entity.Categories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private String postContent;
    private Categories categories;
}
