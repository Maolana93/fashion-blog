package com.week.nine.FashionBlogAPI.repository;

import com.week.nine.FashionBlogAPI.entity.Admin;
import com.week.nine.FashionBlogAPI.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post,Long> {
   Optional<Post> findPostById (Long postId);
}
