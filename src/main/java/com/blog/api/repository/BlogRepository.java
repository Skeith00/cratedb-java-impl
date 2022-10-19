package com.blog.api.repository;

import com.blog.api.model.Post;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends CrudRepository<Post, Integer> {

    List<Post> findAll();

    List<Post> findByTitle(String title);


    @Query("SELECT * FROM Post")
    List<Post> findAllPosts();

    // Since version 2.0, Spring Data JDBC supports query methods.
    // That is, if we name our query method including the keywords, for example, findByFirstName, Spring Data JDBC will generate the query object automatically.
    @Modifying
    @Query("UPDATE post SET body = :body WHERE title = :title")
    boolean updateByTitle(@Param("title") String title, @Param("body") String body);

}