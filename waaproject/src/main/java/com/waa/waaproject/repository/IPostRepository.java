package com.waa.waaproject.repository;

import com.waa.waaproject.domain.Post;
import com.waa.waaproject.dto.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {
    //    public List<Post> getPosts();
//    public void save(Post post);
//    public void delete(int id);
//    public void update(int id, Post post);
//    public Post getPostById(int id);
//    public List<Post> getPostByAuther(String author);
}
