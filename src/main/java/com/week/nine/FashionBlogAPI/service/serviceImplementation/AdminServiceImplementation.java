package com.week.nine.FashionBlogAPI.service.serviceImplementation;

import com.week.nine.FashionBlogAPI.dto.request.AdminLoginRequest;
import com.week.nine.FashionBlogAPI.dto.request.AdminSignUpRequest;
import com.week.nine.FashionBlogAPI.dto.request.PostEditRequest;
import com.week.nine.FashionBlogAPI.dto.request.PostRequest;
import com.week.nine.FashionBlogAPI.dto.response.*;
import com.week.nine.FashionBlogAPI.entity.Admin;
import com.week.nine.FashionBlogAPI.entity.Post;
import com.week.nine.FashionBlogAPI.exception.ResourceNotFoundException;
import com.week.nine.FashionBlogAPI.repository.AdminRepository;
import com.week.nine.FashionBlogAPI.repository.PostRepository;
import com.week.nine.FashionBlogAPI.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {
    private final AdminRepository adminRepository;
    private final PostRepository postRepository;

    @Override
    public AdminSignUpResponse signUp(AdminSignUpRequest adminSignUpRequest) {
        Optional<Admin> optionalAdmin = adminRepository.findAdminByEmail(adminSignUpRequest.getEmail());
        if (optionalAdmin.isPresent()) {
            throw new ResourceNotFoundException("user already exist");
        }
        Admin admin = Admin.builder()
                .name(adminSignUpRequest.getName())
                .email(adminSignUpRequest.getEmail())
                .password(adminSignUpRequest.getPassword())
                .build();
        Admin saveAdmin = adminRepository.save(admin);
        return AdminSignUpResponse.builder()
                .id(saveAdmin.getId())
                .email(saveAdmin.getEmail())
                .build();

    }


    @Override
    public AdminLoginResponse login(AdminLoginRequest adminLoginRequest, HttpServletRequest request) {
        Optional<Admin> optionalAdmin = adminRepository.findAdminByEmail(adminLoginRequest.getEmail());
        if (optionalAdmin.isEmpty()) {
            throw new ResourceNotFoundException("incorrect credentials");
        }
        Admin admin = optionalAdmin.get();
        if (!adminLoginRequest.getPassword().equals(admin.getPassword())) {
            throw new ResourceNotFoundException("invalid email and password");
        }

        HttpSession session = request.getSession();
        session.setAttribute("Admin", admin);
        return AdminLoginResponse.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .message("Admin " + admin.getName() + " You are welcome")
                .build();
    }

    @Override
    public PostResponse createdPost(PostRequest postRequest, Long adminId) {
        Optional<Admin> optionalAdmin = adminRepository.findAdminById(adminId);
        if(optionalAdmin.isEmpty()){
            throw new ResourceNotFoundException("Admin dose not exist");
        }
        Admin admin= optionalAdmin.get();
                //create new Post
        Post newPost= Post.builder()
                .title(postRequest.getTitle())
                .postContent(postRequest.getPostContent())
                .categories(postRequest.getCategories())
                .createdAt(LocalDateTime.now())
                .admin(admin)
                .build();
        Post savedPost = postRepository.save(newPost);
        return  PostResponse.builder()
                .id(savedPost.getId())
                .title(savedPost.getTitle())
                .postContent(savedPost.getPostContent())
                .categories(savedPost.getCategories())
                .createdAt(LocalDateTime.now())
                .build();
         }

    @Override
    public PostEditResponse editedPost(PostEditRequest postEditRequest, Long adminId, Long postId) {
        Optional<Admin> optionalAdmin = adminRepository.findAdminById(adminId);
        if(optionalAdmin.isEmpty()){
            throw new ResourceNotFoundException("You are not Admin");
        }else {
            Admin admin = optionalAdmin.get();
            Optional<Post> optionalPost = postRepository.findPostById(postId);
            if (optionalPost.isEmpty()) {
                throw new ResourceNotFoundException("The post dose not exist");
            } else {
                Post post = optionalPost.get();
                if (!admin.getId().equals(post.getAdmin().getId())) {
                    throw new ResourceNotFoundException("You are not Authorised to edit the post");
                }
                post.setTitle(postEditRequest.getTitle());
                post.setPostContent(postEditRequest.getPostContent());
                post.setCategories(postEditRequest.getCategories());
                Post editedPost = postRepository.save(post);
                return PostEditResponse.builder()
                        .id(editedPost.getId())
                        .title(editedPost.getTitle())
                        .categories(editedPost.getCategories())
                        .postContent(editedPost.getPostContent())
                        .editedAt(LocalDateTime.now())
                        .build();
            }
        }

    }

    @Override
    public PostDeleteResponse deletePost(Long adminId, Long postId) {
        Optional<Admin> optionalAdmin =adminRepository.findAdminById(adminId);
        if(optionalAdmin.isEmpty()){
            throw new ResourceNotFoundException("You are not admin");
        }
        Admin admin = optionalAdmin.get();
        Optional<Post> optionalPost = postRepository.findPostById(postId);
        if(optionalPost.isEmpty()){
            throw  new ResourceNotFoundException("Post dose not exist");
        }
        Post post = optionalPost.get();
        if(!admin.getId().equals(post.getAdmin().getId())){
            throw  new ResourceNotFoundException("You are not Authorised to delete the post");
        }
        postRepository.delete(post);
        return PostDeleteResponse.builder()
                .message("You have deleted your post")
                .build();

    }
}


