package com.week.nine.FashionBlogAPI.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Admin extends BaseClass {
    @Column(nullable = false)
    private String email;
    private String name;
    @Column(length = 20,nullable = false)
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private List <Post> posts =new ArrayList<>();

}
