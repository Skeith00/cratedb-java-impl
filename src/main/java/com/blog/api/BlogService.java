package com.blog.api;

import com.blog.api.model.Metadata;
import com.blog.api.model.Post;
import com.blog.api.model.enums.Category;
import com.blog.api.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
    public void createPost() {
        Post post = new Post();
        post.setId(1);
        post.setTitle("test");
        post.setBody("test body");
        post.setCategories(List.of(Category.FOOD.toString()));
        post.setCreatedAt(Instant.now());
        post.setTags(List.of("foodie", "paella"));
        post.setMetadata(List.of(new Metadata("country", List.of("Spain"))));
        post.setArchived(false);
        blogRepository.save(post);
    }

    public List<Post> fetchData() {
        return blogRepository.findAll();
    }
}
