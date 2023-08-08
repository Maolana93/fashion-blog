package com.week.nine.FashionBlogAPI.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table
public class BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;


}
