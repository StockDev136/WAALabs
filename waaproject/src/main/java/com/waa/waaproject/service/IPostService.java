package com.waa.waaproject.service;

import com.waa.waaproject.domain.Post;
import com.waa.waaproject.dto.PostDto;

import java.util.List;

public interface IPostService {
    List<PostDto> getPost();
    void save(PostDto post);
    void delete(Long id);

    void update(Long id, PostDto post);
    PostDto getById(Long id);
    List<PostDto> getPostByAuthor(String author);
}
