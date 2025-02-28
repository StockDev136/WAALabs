package com.waa.waaproject.controller;


import com.waa.waaproject.domain.Comment;
import com.waa.waaproject.domain.Post;
import com.waa.waaproject.domain.User;
import com.waa.waaproject.dto.UserDto;
import com.waa.waaproject.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    public final IUserService userservice;

    @Autowired
    public UserController(IUserService userservice) {
        this.userservice = userservice;
    }

    @GetMapping
    public List<UserDto> getUsers(@RequestParam(value = "title" ,required = false) String title) {
        return title == null? userservice.findAll():userservice.findUsersPostByTitle(title);
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userservice.findById(id);
    }

    @PostMapping
    public void save(@RequestBody User user) {
        userservice.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userservice.delete(id);
    }

    @PostMapping("/{id}/posts")
    public void createUserPost(@PathVariable Long id, @RequestBody Post post){
        userservice.createUserPost(id, post);
    }

    @GetMapping("/{id}/posts")
    public List<Post> findUserPost(@PathVariable Long id){
        return userservice.findUserPosts(id);
    }

    @GetMapping("/posts")
    public List<User> findUsersWithMoreThanOnePosts(@RequestParam(value = "numpost" ,required = false, defaultValue = "0")  int numpost){
        return numpost == 0? userservice.findUsersWithMoreThanOnePosts():userservice.findUsersWithMoreThanNPosts(numpost);
    }

    @GetMapping("/{userid}/posts/{postid}/comments/{commentsid}")
    public Comment findCommentByUserIdByPostIdByCommentId(@PathVariable Long userid,
                                                          @PathVariable Long postid,
                                                          @PathVariable Long commentsid){
        return userservice.findCommentByUserIdByPostIdByCommentId(userid, postid, commentsid);
    }
    @GetMapping("/{userid}/posts/{postid}")
    public Post findPostByUserIdByPostId(@PathVariable Long userid,
                                          @PathVariable Long postid){
        return userservice.findPostByUserIdByPostId(userid, postid);
    }

}
