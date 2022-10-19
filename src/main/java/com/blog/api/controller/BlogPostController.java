package com.blog.api.controller;

import com.blog.api.BlogService;
import com.blog.api.model.Post;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/blog/post")
public class BlogPostController {

    private final BlogService blogService;

    public BlogPostController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Post>> getAllBlogPosts() throws SQLException {
        return ResponseEntity.ok().body(blogService.fetchData());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPost() {
        blogService.createPost();
        return ResponseEntity.ok().build();
    }

}
