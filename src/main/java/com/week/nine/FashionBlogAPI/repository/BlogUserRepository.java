package com.week.nine.FashionBlogAPI.repository;

import com.week.nine.FashionBlogAPI.entity.Admin;
import com.week.nine.FashionBlogAPI.entity.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogUserRepository extends JpaRepository<BlogUser, Long> {
    Optional<BlogUser >findBlogUserByEmail (String email);
   Optional<BlogUser> findBlogUserById (Long blogUserId);
}
