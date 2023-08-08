package com.week.nine.FashionBlogAPI.controller;

import com.week.nine.FashionBlogAPI.dto.request.*;
import com.week.nine.FashionBlogAPI.service.BlogUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/blog-api")
public class BlogController {
    public final BlogUserService blogUserService;

    @PostMapping("/signup/blogUsers")
    public ResponseEntity<?> SignUpBlogUsers(@RequestBody @Valid BlogUserSignUpRequest blogUserSignUpRequest){
        var response = blogUserService.signUp(blogUserSignUpRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/userLogin")
    public ResponseEntity<?> login( @RequestBody BlogUserLoginRequest blogUserLoginRequest,
                                    HttpServletRequest request){
        var BlogUserLoginResponse = blogUserService.login(blogUserLoginRequest, request);
        return ResponseEntity.ok(BlogUserLoginResponse);
    }
    @PostMapping("/createComment/{postId}/{blogUser}")
    public ResponseEntity<?> makeComment (@RequestBody BlogUserCommentRequest blogUserCommentRequest,
                                          @PathVariable ("postId") Long postId, @PathVariable ("blogUser") Long blogUSerId){
        var response = blogUserService.makeComment(blogUserCommentRequest,postId,blogUSerId);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/likePost/{PostId}/{BlogUserId}")
    public ResponseEntity<?> likePost(@PathVariable("PostId") Long postId,@PathVariable("BlogUserId")Long blogUserId){
        var response = blogUserService.likePost(postId,blogUserId);
        return ResponseEntity.ok(response);
    }
}
