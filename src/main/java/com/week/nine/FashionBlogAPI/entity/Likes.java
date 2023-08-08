package com.week.nine.FashionBlogAPI.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
public class Likes extends BaseClass{
    @ManyToOne
    @JoinColumn
    private Post posts;
    @ManyToOne
    @JoinColumn
    private Comment comments;
    @ManyToOne
    @JoinColumn
    private BlogUser blogUser;

}
