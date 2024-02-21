package com.example.demo.posts.posts.application.find;

import com.example.demo.posts.posts.domain.model.PostQuery;
import com.example.demo.posts.posts.domain.repository.IPostQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostFindUseCase {
    private final IPostQueryRepository postQueryRepository;
    public List<PostQuery> findAllPosts() {
        return this.postQueryRepository.findAllPosts();
    }

    public Optional<PostQuery> findById(Integer id) {
        Optional<PostQuery> byId = this.postQueryRepository.findById(id);
        return byId;
    }

    public List<PostQuery> findAllPostByUser(Integer userId) {
        return this.postQueryRepository.searchBy(Map.of("userId", String.valueOf(userId)));
    }
}
