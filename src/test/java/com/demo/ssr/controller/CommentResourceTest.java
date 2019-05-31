package com.demo.ssr.controller;

import com.demo.ssr.comments.Comment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
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

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommentResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_getCommentsApi_shouldReturnListOfComments() {
        try {
            this.mockMvc.perform(MockMvcRequestBuilders
                    .get("/api/comments"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(2)))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test_postComment() {
        try {
            this.mockMvc.perform(MockMvcRequestBuilders
                    .post("/api/comments")
                    .content(asJsonString(new Comment("Vikas", "Spring boot test")))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test_postEmptyComment_shouldThrowBadRequest() {
        try {
            this.mockMvc.perform(MockMvcRequestBuilders
                    .post("/api/comments")
                    .content(asJsonString(new Comment()))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void test_postNullComment_shouldThrowBadRequest() {
        try {
            this.mockMvc.perform(MockMvcRequestBuilders
                    .post("/api/comments")
                    .content("null")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
