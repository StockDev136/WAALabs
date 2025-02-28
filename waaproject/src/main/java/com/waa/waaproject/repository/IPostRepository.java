package com.waa.waaproject.repository;

import com.waa.waaproject.domain.Post;
import com.waa.waaproject.dto.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%') )")
    List<Post> getPostByTitle(String title);
//    @Query("SELECT p FROM Post p WHERE LOWER(p.author) LIKE LOWER(CONCAT('%', :author, '%') )")
//    List<PostDto> getPostByAuthor(String author);
    @Query("SELECT p FROM Post p WHERE LOWER(p.author) LIKE LOWER(CONCAT('%', :author, '%')) AND LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Post> findByAuthorAndTitle(@Param("author") String author, @Param("title") String title);
    //    public List<Post> getPosts();
//    public void save(Post post);
//    public void delete(int id);
//    public void update(int id, Post post);
//    public Post getPostById(int id);
//    public List<Post> getPostByAuther(String author);
}
