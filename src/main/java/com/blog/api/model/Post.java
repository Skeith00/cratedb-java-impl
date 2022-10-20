package com.blog.api.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Table("post")
public class Post /*implements Persistable<Integer>*/ {
    @Id
    private Integer id;
    private String title;
    private String body;
    private List<String> categories = new ArrayList<>();
    //created_at
    @CreatedDate
    @Column("created_at")
    private Instant createdAt;
    private List<String> tags = new ArrayList<>();

    @Column("metadata")
    private List<Metadata> metadata;
    //location GEO_SHAPE,
    private boolean archived;

/*    @Transient
    @JsonIgnore
    private Boolean isInsert;

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    @JsonIgnore
    public boolean isNew() {
        return isInsert;
    }*/

}
