package com.demo.ssr.controller;

import com.demo.ssr.comments.Comment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommentResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_getCommentsApi() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/comments")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void test_postComment() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/comments")
                .content(asJsonString(new Comment("Vikas", "Spring boot test")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
