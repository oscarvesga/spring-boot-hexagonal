package com.example.demo.posts.posts.infraestructure.inbound.controller;

import com.example.demo.posts.posts.application.find.PostFindUseCase;
import com.example.demo.posts.posts.domain.model.PostQuery;
import com.example.demo.posts.posts.infraestructure.inbound.controllers.UserController;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PostFindUseCase postFindUseCase;

    @Test
    void findAllPostByUserId_withResponseOk() throws Exception{
        List<PostQuery> postList = new ArrayList<PostQuery>();
        for (int i = 101; i < 104; i++) {
            PostQuery p = new PostQuery(1L, (long) i, "Title Test "+i, "Description "+i);
            postList.add(p);
        }
        when(this.postFindUseCase.findAllPostByUser(1)).thenReturn(postList);

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1/posts")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)))
        .andDo(print());
    }
}
