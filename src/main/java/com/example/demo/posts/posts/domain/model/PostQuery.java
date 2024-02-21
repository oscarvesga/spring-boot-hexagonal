package com.example.demo.posts.posts.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class PostQuery {
    private Long userId;
    private Long id;
    private String title;
    private String body;
}
