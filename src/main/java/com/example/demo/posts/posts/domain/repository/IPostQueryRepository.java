package com.example.demo.posts.posts.domain.repository;

import com.example.demo.posts.posts.domain.model.PostQuery;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IPostQueryRepository {
    Optional<PostQuery> findById(int id);
    List<PostQuery> searchBy(Map<String, String> params);
    List<PostQuery> findAllPosts();
}
