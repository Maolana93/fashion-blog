package com.week.nine.FashionBlogAPI.service;

import com.week.nine.FashionBlogAPI.dto.request.AdminLoginRequest;
import com.week.nine.FashionBlogAPI.dto.request.AdminSignUpRequest;
import com.week.nine.FashionBlogAPI.dto.request.PostEditRequest;
import com.week.nine.FashionBlogAPI.dto.request.PostRequest;
import com.week.nine.FashionBlogAPI.dto.response.*;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {
     AdminSignUpResponse signUp(AdminSignUpRequest adminSignUpRequest);

     AdminLoginResponse login(AdminLoginRequest adminLoginRequest, HttpServletRequest request);

     PostResponse createdPost (PostRequest postRequest,Long adminId);
     PostEditResponse editedPost (PostEditRequest postEditRequest, Long adminId, Long postId);
     PostDeleteResponse deletePost(Long adminId , Long postId);
}
