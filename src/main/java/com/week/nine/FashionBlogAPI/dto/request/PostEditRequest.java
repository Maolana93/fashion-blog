package com.week.nine.FashionBlogAPI.dto.request;

import com.week.nine.FashionBlogAPI.entity.Categories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostEditRequest {
    private Long id;
    private String title;
    private String postContent;
    private Categories categories;
}
