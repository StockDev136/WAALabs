package com.waa.waaproject.dto;

import lombok.Data;

@Data
public class PostDto {
    long id;
    String title;
    String content;
    String author;
    Long userId;
}

