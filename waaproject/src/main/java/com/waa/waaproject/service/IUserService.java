package com.waa.waaproject.service;

import com.waa.waaproject.domain.Post;
import com.waa.waaproject.domain.User;
import com.waa.waaproject.dto.PostDto;
import com.waa.waaproject.dto.UserDto;

import java.util.List;

public interface IUserService {
    List<UserDto> findAll();
    UserDto findById(Long id);
    void save(User user);
    void delete(Long id);
    void createUserPost(Long userid, Post post);
    List<Post> findUserPosts(Long id);
    List<User> findUsersWithMoreThanOnePosts();
}
