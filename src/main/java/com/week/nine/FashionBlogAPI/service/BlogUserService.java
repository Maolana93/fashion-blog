package com.week.nine.FashionBlogAPI.service;

import com.week.nine.FashionBlogAPI.dto.request.BlogUserCommentRequest;
import com.week.nine.FashionBlogAPI.dto.request.BlogUserLikeRequest;
import com.week.nine.FashionBlogAPI.dto.request.BlogUserLoginRequest;
import com.week.nine.FashionBlogAPI.dto.request.BlogUserSignUpRequest;
import com.week.nine.FashionBlogAPI.dto.response.BlogUserCommentResponse;
import com.week.nine.FashionBlogAPI.dto.response.BlogUserLikeResponse;
import com.week.nine.FashionBlogAPI.dto.response.BlogUserLoginResponse;
import com.week.nine.FashionBlogAPI.dto.response.BlogUserSignUpResponse;

import javax.servlet.http.HttpServletRequest;

public interface BlogUserService {
    BlogUserSignUpResponse signUp(BlogUserSignUpRequest blogUserSignUpRequest);
    BlogUserLoginResponse login(BlogUserLoginRequest blogUserLoginRequest, HttpServletRequest request);
    BlogUserCommentResponse makeComment(BlogUserCommentRequest blogUserCommentRequest,Long postId,Long blogUserId);
    BlogUserLikeResponse likePost (Long postId, Long blogUserId);
}
