package com.example.demo.posts.posts.infraestructure;

import com.example.demo.posts.posts.domain.model.PostCommand;
import com.example.demo.posts.posts.domain.model.PostQuery;
import com.example.demo.posts.posts.domain.repository.IPostCommandRepository;
import com.example.demo.posts.posts.infraestructure.outbound.database.IPostEntityRepository;
import com.example.demo.posts.posts.infraestructure.outbound.database.IPostMapper;
import com.example.demo.posts.posts.infraestructure.outbound.database.PostEntity;
import com.example.demo.posts.posts.infraestructure.outbound.external.IJsonPlaceHolderAPIClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostCommandRepositoryImp implements IPostCommandRepository {
    private final IJsonPlaceHolderAPIClient jsonPlaceHolderAPIClient;
    private final IPostEntityRepository postEntityRepository;
    private final IPostMapper postMapper;
    @Override
    public Optional<PostQuery> createPost(PostCommand postCommand) {
        PostEntity postEntity = postMapper.toPostEntity(postCommand);
        PostEntity value = postEntityRepository.save(postEntity);
        return Optional.ofNullable(postMapper.toPostQuery(value));
    }

    @Override
    public Optional<PostQuery> updatePost(PostCommand postCommand) {
        return Optional.empty();
    }

    @Override
    public void deletePost(int id) {

    }
}
