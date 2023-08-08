package com.week.nine.FashionBlogAPI.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Post extends BaseClass{
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime editedAt;
    private String postContent;
    @OneToMany
    @JoinColumn
    private List <Likes> likes=new ArrayList<>();
    @ManyToOne
    @JoinColumn
    private BlogUser blogUser;
    @ManyToOne
    @JoinColumn
    private Admin admin;
    @Enumerated (EnumType.STRING)
    private Categories categories;
    @OneToMany
    @JoinColumn(name = "post_id")
    private List<Comment> comments=new ArrayList<>();


}
