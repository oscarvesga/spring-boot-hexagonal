package com.example.demo.posts.posts.application.create;

import com.example.demo.posts.posts.domain.model.PostCommand;
import com.example.demo.posts.posts.domain.model.PostQuery;
import com.example.demo.posts.posts.domain.repository.IPostCommandRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PostCreateUseCaseTest {
    @Autowired
    private IPostCommandRepository postCommandRepository;

    @Test
    void createPost_successfully() {
        PostCreateUseCase postCreateUseCase = new PostCreateUseCase(this.postCommandRepository);
        PostCommand postCommand = PostCommand.builder()
                .userId(3L)
                .title("Title UseCaseTest Created")
                .body("Description UseCaseTest Created")
                .build();
        PostQuery postQuery = new PostQuery(
                1L,
                101L,
                "Title Test Created",
                "Description Test Created");

        //when(this.postCommandRepository.createPost(any(PostCommand.class))).thenReturn(Optional.of(postQuery));
        Optional<PostQuery> postCreated = postCreateUseCase.createPost(postCommand);
        System.out.println(postCreated.toString());

        assertFalse(postCreated.isEmpty());
        assertEquals("Description UseCaseTest Created",postCreated.get().getBody());
    }
}
