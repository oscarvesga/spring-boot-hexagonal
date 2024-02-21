package com.example.demo.posts.posts.application.create;

import com.example.demo.posts.posts.domain.model.PostCommand;
import com.example.demo.posts.posts.domain.model.PostQuery;
import com.example.demo.posts.posts.domain.repository.IPostCommandRepository;
import com.example.demo.posts.posts.infraestructure.outbound.database.IPostEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostCreateUseCase {
    private final IPostCommandRepository postCommandRepository;

    public Optional<PostQuery> createPost(PostCommand postCommand) {
        return postCommandRepository.createPost(postCommand);
    }

}
