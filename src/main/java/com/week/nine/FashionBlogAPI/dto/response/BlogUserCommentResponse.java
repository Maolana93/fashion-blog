package com.week.nine.FashionBlogAPI.dto.response;

import com.week.nine.FashionBlogAPI.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogUserCommentResponse {
    private Post post;
    private String comment;
    private LocalDateTime createdAt;
}
