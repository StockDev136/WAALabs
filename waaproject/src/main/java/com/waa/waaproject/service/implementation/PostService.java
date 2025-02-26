package com.waa.waaproject.service.implementation;

import com.waa.waaproject.domain.Post;
import com.waa.waaproject.dto.PostDto;
import com.waa.waaproject.helper.ListMapper;
import com.waa.waaproject.repository.IPostRepository;
import com.waa.waaproject.service.IPostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService {
    private final IPostRepository postrepository;
@Autowired
    public PostService(IPostRepository postrepository) {
        this.postrepository = postrepository;
    }

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    public List<PostDto> getPost() {
        return listMapper.mapList(postrepository.getPosts(), new PostDto());
    }

    @Override
    public void save(Post post) {
        postrepository.save(modelMapper.map(post, Post.class));
    }

    @Override
    public void delete(int id) {
        postrepository.delete(id);
    }

    @Override
    public void update(int id, PostDto post) {
        postrepository.update(id, modelMapper.map(post, Post.class));
    }

    @Override
    public PostDto getById(int id) {
        return modelMapper.map(postrepository.getPostById(id), PostDto.class);
    }

    @Override
    public List<PostDto> getPostByAuthor(String author) {
        return listMapper.mapList(postrepository.getPostByAuther(author), new PostDto());
    }
}
