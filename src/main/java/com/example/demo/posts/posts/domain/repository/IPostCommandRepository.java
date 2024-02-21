package com.example.demo.posts.posts.domain.repository;

import com.example.demo.posts.posts.domain.model.PostCommand;
import com.example.demo.posts.posts.domain.model.PostQuery;

import java.util.Optional;

public interface IPostCommandRepository {
    Optional<PostQuery> createPost(PostCommand postCommand);
    Optional<PostQuery> updatePost(PostCommand postCommand);
    void deletePost(int id);
}
