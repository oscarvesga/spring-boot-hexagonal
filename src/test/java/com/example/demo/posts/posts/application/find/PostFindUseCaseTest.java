package com.example.demo.posts.posts.application.find;

import com.example.demo.posts.posts.domain.model.PostQuery;
import com.example.demo.posts.posts.domain.repository.IPostQueryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PostFindUseCaseTest {
    @Autowired
    private IPostQueryRepository postQueryRepository;

    @Test
    void findAllPosts_successfully() {
        PostFindUseCase postFindUseCase = new PostFindUseCase(postQueryRepository);
        List<PostQuery> postQueryList = postFindUseCase.findAllPosts();

        //System.out.println(postQueryList);
        //assertEquals(100,postQueryList.size());
        assertTrue(postQueryList.size() > 1);
    }
}
