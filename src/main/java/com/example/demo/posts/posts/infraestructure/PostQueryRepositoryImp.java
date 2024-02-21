package com.example.demo.posts.posts.infraestructure;

import com.example.demo.posts.posts.domain.model.PostQuery;
import com.example.demo.posts.posts.domain.repository.IPostQueryRepository;
import com.example.demo.posts.posts.infraestructure.outbound.database.IPostEntityRepository;
import com.example.demo.posts.posts.infraestructure.outbound.database.IPostMapper;
import com.example.demo.posts.posts.infraestructure.outbound.database.PostEntity;
import com.example.demo.posts.posts.infraestructure.outbound.external.IJsonPlaceHolderAPIClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostQueryRepositoryImp implements IPostQueryRepository {
    private final IJsonPlaceHolderAPIClient jsonPlaceHolderAPIClient;
    private final IPostEntityRepository postEntityRepository;
    private final IPostMapper postMapper;
    @Override
    public Optional<PostQuery> findById(int id) {
        try {
            PostQuery postById = this.jsonPlaceHolderAPIClient.findPostById(id);
            return Optional.ofNullable(postById);
        } catch (FeignException e) {
            if (e.status() == 404) {
                Optional<PostEntity> postEntityById = this.postEntityRepository.findById((long) id);
                return postEntityById.map(postMapper::toPostQuery);
            }
            return Optional.empty();
        }
    }

    @Override
    public List<PostQuery> searchBy(Map<String, String> params) {
        List<PostQuery> postQueryLst = this.jsonPlaceHolderAPIClient.searchByParam(params);
        List<PostEntity> postEntityLst = postEntityRepository.findByIdUser(Long.valueOf(params.get("userId")));
        postQueryLst.addAll(postMapper.toPostQueryLst(postEntityLst));

        return postQueryLst;
    }

    @Override
    public List<PostQuery> findAllPosts() {
        List<PostQuery> postPlaceHolderLst = this.jsonPlaceHolderAPIClient.getAllPost();
        List<PostEntity> postEntityLst = this.postEntityRepository.findAll();
        postPlaceHolderLst.addAll(postMapper.toPostQueryLst(postEntityLst));

        return postPlaceHolderLst;
    }
}
