package com.example.demo.posts.posts.infraestructure.outbound.external;

import com.example.demo.posts.posts.domain.model.PostCommand;
import com.example.demo.posts.posts.domain.model.PostQuery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "${json-placeholder.client.name}", url = "${json-placeholder.url}")
public interface IJsonPlaceHolderAPIClient {
    @PostMapping
    PostQuery create(@RequestBody PostCommand request);

    @GetMapping("/{id}")
    PostQuery findPostById(@PathVariable Integer id);

    @GetMapping
    List<PostQuery> getAllPost();

    @GetMapping()
    List<PostQuery> searchByParam(@RequestParam Map<String, String> params);
}
