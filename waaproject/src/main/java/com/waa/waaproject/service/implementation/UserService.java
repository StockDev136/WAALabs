package com.waa.waaproject.service.implementation;

import com.waa.waaproject.domain.Comment;
import com.waa.waaproject.domain.Post;
import com.waa.waaproject.domain.User;
import com.waa.waaproject.dto.CommentDto;
import com.waa.waaproject.dto.PostDto;
import com.waa.waaproject.dto.UserDto;
import com.waa.waaproject.helper.ListMapper;
import com.waa.waaproject.repository.IUserRepository;
import com.waa.waaproject.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    public final IUserRepository userRepository;
    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;


    @Override
    public List<UserDto> findAll() {
        return listMapper.mapList(userRepository.findAll(), new UserDto());
    }

    @Override
    public UserDto findById(Long id) {
        User u = userRepository.findById(id).orElse(null);
        return modelMapper.map(u, UserDto.class);
    }

    @Override
    public void save(User user) {
        userRepository.save(modelMapper.map(user, User.class));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void createUserPost(Long userid, Post post) {
        User u = userRepository.findById(userid).orElse(null);
        u.getPosts().add(post);
        userRepository.save(u);
    }

    public List<Post> findUserPosts(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user.getPosts();
    }

    @Override
    public List<User> findUsersWithMoreThanOnePosts() {
        return listMapper.mapList(userRepository.findUsersWithMoreThanOnePosts(), new User());
    }

    @Override
    public List<User> findUsersWithMoreThanNPosts(int n) {
        return userRepository.findUsersWithMoreThanNPosts(n);
    }

    @Override
    public List<UserDto> findUsersPostByTitle(String title) {
        return listMapper.mapList(userRepository.findUsersPostByTitle(title), new UserDto());
    }

    @Override
    public Comment findCommentByUserIdByPostIdByCommentId(Long userid, Long postid, Long commentid) {
        return userRepository.findCommentByUserIdByPostIdByCommentId(userid, postid, commentid);
    }

    @Override
    public Post findPostByUserIdByPostId(Long userid, Long postid) {
        return userRepository.findPostByUserIdByPostId(userid, postid);
    }
}
