package com.waa.waaproject.repository;

import com.waa.waaproject.domain.Post;
import com.waa.waaproject.dto.PostDto;

import java.util.List;

public interface IPostRepository {

    public List<Post> getPosts();
    public void save(Post post);
    public void delete(int id);
    public void update(int id, Post post);
    public Post getPostById(int id);
    public List<Post> getPostByAuther(String author);
}
