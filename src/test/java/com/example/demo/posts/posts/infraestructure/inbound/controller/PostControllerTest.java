package com.example.demo.posts.posts.infraestructure.inbound.controller;

import com.example.demo.posts.posts.application.create.PostCreateUseCase;
import com.example.demo.posts.posts.application.find.PostFindUseCase;
import com.example.demo.posts.posts.domain.model.PostCommand;
import com.example.demo.posts.posts.domain.model.PostQuery;
import com.example.demo.posts.posts.infraestructure.inbound.controllers.PostController;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PostController.class)
public class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PostCreateUseCase postCreateUseCase;

    @MockBean
    private PostFindUseCase postFindUseCase;

    @Test
    void createPost_withResponseCreated() throws Exception {
        PostCommand postCommand = PostCommand.builder()
                .userId(1L)
                .title("Title Test Created")
                .body("Description Test Created")
                .build();
        PostQuery postQuery = new PostQuery(
                1L,
                101L,
                "Title Test Created",
                "Description Test Created");

        when(this.postCreateUseCase.createPost(any(PostCommand.class))).thenReturn(Optional.of(postQuery));
        String postCommandJson = objectMapper.writeValueAsString(postCommand);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postCommandJson)
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(101))
                .andDo(print());
    }

    @Test
    void findAllPost_withResponseOk() throws Exception{
        List<PostQuery> postList = new ArrayList<PostQuery>();
        for (int i = 101; i < 104; i++) {
            PostQuery p = new PostQuery(1L, (long) i, "Title Test "+i, "Description "+i);
            postList.add(p);
        }
        when(this.postFindUseCase.findAllPosts()).thenReturn(postList);

        mockMvc.perform(MockMvcRequestBuilders.get("/posts")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(print());
    }

    @Test
    void findPostById_withResponseOk() throws Exception{
        PostQuery post = new PostQuery(1L, 101L, "Title Test ", "Description ");
        when(this.postFindUseCase.findById(1)).thenReturn(Optional.of(post));

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(101))
                .andDo(print());
    }

    @Test
    void findPostById_withResponseNotFound() throws Exception{
        when(this.postFindUseCase.findById(1)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isNotFound())
                .andDo(print());
    }
}
