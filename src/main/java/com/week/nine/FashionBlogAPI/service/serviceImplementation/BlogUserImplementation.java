package com.week.nine.FashionBlogAPI.service.serviceImplementation;

import com.week.nine.FashionBlogAPI.dto.request.BlogUserCommentRequest;
import com.week.nine.FashionBlogAPI.dto.request.BlogUserLoginRequest;
import com.week.nine.FashionBlogAPI.dto.request.BlogUserSignUpRequest;
import com.week.nine.FashionBlogAPI.dto.response.*;
import com.week.nine.FashionBlogAPI.entity.BlogUser;
import com.week.nine.FashionBlogAPI.entity.Comment;
import com.week.nine.FashionBlogAPI.entity.Likes;
import com.week.nine.FashionBlogAPI.entity.Post;
import com.week.nine.FashionBlogAPI.exception.ResourceNotFoundException;
import com.week.nine.FashionBlogAPI.repository.BlogUserRepository;
import com.week.nine.FashionBlogAPI.repository.CommentRepository;
import com.week.nine.FashionBlogAPI.repository.PostRepository;
import com.week.nine.FashionBlogAPI.service.BlogUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogUserImplementation implements BlogUserService {
    private  final BlogUserRepository blogUserRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    @Override
    public BlogUserSignUpResponse signUp(BlogUserSignUpRequest blogUserSignUpRequest) {
        Optional<BlogUser> optionalBlogUser = blogUserRepository.findBlogUserByEmail(blogUserSignUpRequest.getEmail());
        if (optionalBlogUser.isPresent()) {
            throw new ResourceNotFoundException("The User Already exit");
        }
        BlogUser blogUser = BlogUser.builder()
                .email(blogUserSignUpRequest.getEmail())
                .password(blogUserSignUpRequest.getPassword())
                .build();
        BlogUser saveBlogUser = blogUserRepository.save(blogUser);
        return BlogUserSignUpResponse.builder()
                .id(saveBlogUser.getId())
                .email(saveBlogUser.getEmail())
                .build();




    }

    @Override
    public BlogUserLoginResponse login(BlogUserLoginRequest blogUserLoginRequest, HttpServletRequest request) {
        Optional<BlogUser> optionalBlogUser = blogUserRepository.findBlogUserByEmail(blogUserLoginRequest.getEmail());
        if (optionalBlogUser.isEmpty()) {
            throw new ResourceNotFoundException("Info not found ");
        }
        BlogUser blogUser = optionalBlogUser.get();
        if (!blogUserLoginRequest.getPassword().equals(blogUser.getPassword())){
            throw new ResourceNotFoundException("Invalid Email and password");
    }
        HttpSession session= request.getSession();
        session.setAttribute("BlogUser",blogUser);
        return BlogUserLoginResponse.builder()
                .email(blogUser.getEmail())
                .id(blogUser.getId())
                .message("You Are Welcome")
                .build();

    }

    @Override
    public BlogUserCommentResponse makeComment(BlogUserCommentRequest blogUserCommentRequest, Long postId, Long blogUserId) {
        Optional<Post> optionalPost = postRepository.findPostById(postId);
        if (optionalPost.isEmpty()){
            throw new ResourceNotFoundException("Post does not exist");
        }
        Optional<BlogUser> optionalBlogUser = blogUserRepository.findBlogUserById(blogUserId);
        if( optionalBlogUser.isEmpty()){
            throw new ResourceNotFoundException("BlogUser does not exist ");
        }
        Post post =optionalPost.get();
        BlogUser blogUser= optionalBlogUser.get();
        // Creating new Comment
        Comment newComment= Comment.builder()
                .Content(blogUserCommentRequest.getComment())
                .posts(post)
                .blogUser(blogUser)
                .build();
        Comment savedComment = commentRepository.save(newComment);
        return BlogUserCommentResponse.builder()
                .post(post)
                .comment(savedComment.getContent())
                .createdAt(LocalDateTime.now())
                .build();
    }

    @Override
    public BlogUserLikeResponse likePost(Long postId, Long blogUserId) {
        Optional<Post> optionalPost= postRepository.findPostById(postId);
        if (optionalPost.isEmpty()) {
            throw new ResourceNotFoundException("Post does not exist");
        }
        Optional<BlogUser> optionalBlogUser = blogUserRepository.findBlogUserById(blogUserId);
        if( optionalBlogUser.isEmpty()) {
            throw new ResourceNotFoundException("BlogUser does not exist ");
        }
        Post post = optionalPost.get();
        BlogUser blogUser= optionalBlogUser.get();
        // check if the user has already liked the post
        boolean hasLiked = post.getLikes().stream().anyMatch(likes -> likes.getBlogUser().getId().equals(blogUserId));
        if (hasLiked){
            throw new ResourceNotFoundException("You have already liked the post");
        }
        // create new like
        Likes newLike = Likes.builder()
                .posts(post)
                .blogUser(blogUser)
                .build();
        post.getLikes().add(newLike);
        blogUserRepository.save(blogUser);
        postRepository.save(post);

        return BlogUserLikeResponse.builder()
                .postId(postId)
                .blogUserId(blogUserId)
                .likedAt(LocalDateTime.now())
                .build();

    }
}
