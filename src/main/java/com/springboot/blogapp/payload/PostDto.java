package com.springboot.blogapp.payload;

import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private long id;
    private String title;
    private String content;
    private String username;
    private Set<CommentDto> comments;
}
