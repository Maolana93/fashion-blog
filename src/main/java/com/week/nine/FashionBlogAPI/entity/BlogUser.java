package com.week.nine.FashionBlogAPI.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table
public class BlogUser extends BaseClass{
    @Column(nullable = false)
    private String email;
    @Column
    private String password;
    @Column
    private String name;
    private Integer likes;
    @Column
    @OneToMany
    @JoinColumn(name = "comments_id")
    private List<Comment> comment = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "blog_id")
    private List<Post> post= new ArrayList<>();

}
