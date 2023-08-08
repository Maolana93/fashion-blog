package com.week.nine.FashionBlogAPI.controller;

import com.week.nine.FashionBlogAPI.dto.request.AdminLoginRequest;
import com.week.nine.FashionBlogAPI.dto.request.AdminSignUpRequest;
import com.week.nine.FashionBlogAPI.dto.request.PostEditRequest;
import com.week.nine.FashionBlogAPI.dto.request.PostRequest;
import com.week.nine.FashionBlogAPI.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/blog-api")

public class AdminController {
    public final AdminService adminService;

    @PostMapping(path = "/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid AdminSignUpRequest request) {
        var response = adminService.signUp(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping(path = "/login")
    public ResponseEntity<?> login( @RequestBody AdminLoginRequest adminLoginRequest,
                                    HttpServletRequest request){
        var AdminLoginResponse = adminService.login(adminLoginRequest, request);
        return ResponseEntity.ok(AdminLoginResponse);
    }
    @PostMapping("/createPost/{AdminId}")
    public ResponseEntity<?> createPost (@RequestBody @Valid PostRequest postRequest, @PathVariable("AdminId") Long adminId){
        var response =adminService.createdPost(postRequest, adminId);
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/editPost/{AdminId}/{PostId}")
    public  ResponseEntity<?> editPost (@RequestBody PostEditRequest postEditRequest,@PathVariable("AdminId") Long adminId,
                                        @PathVariable("PostId") Long postId){
        var response = adminService.editedPost(postEditRequest,adminId,postId);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/deletePost/{AdminId}/{PostId}")
    public  ResponseEntity<?> deletePost(@PathVariable ("AdminId") Long adminId,@PathVariable("PostId") Long postId){
         var response =adminService.deletePost(adminId,postId);
         return ResponseEntity.ok(response);
    }
}

