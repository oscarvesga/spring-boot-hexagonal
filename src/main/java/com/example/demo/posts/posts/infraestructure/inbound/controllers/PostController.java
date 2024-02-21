package com.example.demo.posts.posts.infraestructure.inbound.controllers;

import com.example.demo.posts.posts.application.create.PostCreateUseCase;
import com.example.demo.posts.posts.application.find.PostFindUseCase;
import com.example.demo.posts.posts.domain.model.PostCommand;
import com.example.demo.posts.posts.domain.model.PostQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostFindUseCase postFindUseCase;
    private final PostCreateUseCase postCreateUseCase;

    @GetMapping
    public ResponseEntity<?> findAllPosts() {
        return ResponseEntity.ok(this.postFindUseCase.findAllPosts());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Optional<PostQuery> byId = this.postFindUseCase.findById(id);
        if(byId.isPresent()) return ResponseEntity.ok(byId);
        else return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostCommand postCommand) {
        return new ResponseEntity<>(this.postCreateUseCase.createPost(postCommand), HttpStatus.CREATED);
    }
}
