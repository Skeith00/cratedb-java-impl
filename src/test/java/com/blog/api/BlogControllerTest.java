package com.blog.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = BlogApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
class BlogControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getAllBlogPosts_thenStatus200() throws Exception {
        //createTestpost();
        mvc.perform(get("/blog/post")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void publishNewBlogPost_thenStatus200() throws Exception {
        mvc.perform(post("/blog/post")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
