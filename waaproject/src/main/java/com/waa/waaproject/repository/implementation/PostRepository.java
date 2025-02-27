package com.waa.waaproject.repository.implementation;

import com.waa.waaproject.domain.Post;
import com.waa.waaproject.dto.PostDto;
import com.waa.waaproject.repository.IPostRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostRepository {
//    public static List<Post> posts = new ArrayList<>();
//    private static int postId = 125;
//
//    static {
//        posts.add(new Post(120,"Java for Dummies","Basic programming in Java","Java Dummies"));
//        posts.add(new Post(121,"Fundamental of Spring Boot","Spring Boot concepts","Oriely"));
//        posts.add(new Post(122,"React Pro","All about React","Reactors"));
//        posts.add(new Post(123,"JPA Concepts","Advance JPA techniques","JPA Factors"));
//        posts.add(new Post(124,"Spring Security","All About the security","Security Pro"));
//    }
//
//
//
//    @Override
//    public List<Post> getPosts() {
//        return posts;
//    }
//
//    @Override
//    public void save(Post post) {
//        post.setId(postId);
//        postId++;
//        posts.add(post);
//    }
//
//    @Override
//    public void delete(int id) {
//        posts.remove(getPostById(id));
//    }
//
//    @Override
//    public void update(int id, Post post) {
//        Post p = getPostById(id);
//        p.setTitle(post.getTitle());
//        p.setContent(post.getContent());
//        p.setAuthor(post.getAuthor());
//    }
//
//    @Override
//    public Post getPostById(int id) {
//        return posts.stream()
//                .filter(post -> post.getId() == id).
//                findFirst().orElse(null);
//    }
//
//    @Override
//    public List<Post> getPostByAuther(String author) {
//        return posts.stream().filter(post -> post.getAuthor().contains(author)).collect(Collectors.toList());
//    }
}
