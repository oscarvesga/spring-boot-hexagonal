package com.example.demo.posts.posts.infraestructure.outbound.database;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface IPostEntityRepository extends ListCrudRepository<PostEntity, Long> {
    List<PostEntity> findByIdUser(Long userId);
}
