package com.waa.waaproject.controller;

import com.waa.waaproject.domain.Comment;
import com.waa.waaproject.domain.Post;
import com.waa.waaproject.dto.PostDto;
import com.waa.waaproject.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {
    public final IPostService postservice;
@Autowired
    public PostController(IPostService postservice) {
        this.postservice = postservice;
    }

    @GetMapping
    public List<PostDto> getPost(@RequestParam(value = "author" ,required = false) String author,
                                 @RequestParam(value = "title" ,required = false) String title){
        if(title != null && author != null) {
            return postservice.getPostByAuthorAndTitle(author, title);
        } else if (title != null || author != null) {
            return author == null? postservice.getPostByTitle(title):postservice.getPostByAuthor(author);
        }
        return postservice.getPost();
    }

    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable Long id){
        return postservice.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody PostDto post){
        postservice.save(post);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        postservice.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody PostDto post){
        postservice.update(id, post);
    }

    @PostMapping("/{id}/comments")
    public void createPostComment(@PathVariable Long id, @RequestBody Comment comment){
        postservice.createPostComment(id, comment);
    }
}
