package com.waa.waaproject.service;

import com.waa.waaproject.domain.Post;
import com.waa.waaproject.dto.PostDto;

import java.util.List;

public interface IPostService {
    public List<PostDto> getPost();
    public void save(Post post);
    void delete(int id);
    void update(int id, PostDto post);
    PostDto getById(int id);
    List<PostDto> getPostByAuthor(String author);
}
