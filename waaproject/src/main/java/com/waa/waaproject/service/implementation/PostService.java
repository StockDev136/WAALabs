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
import java.util.Optional;
import java.util.stream.Collectors;

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
        return listMapper.mapList(postrepository.findAll(), new PostDto());
    }

    @Override
    public void save(PostDto post) {
        postrepository.save(modelMapper.map(post, Post.class));
    }

    @Override
    public void delete(Long id) {
        postrepository.deleteById(id);
    }

    @Override
    public void update(Long id, PostDto post) {
        Optional<Post> p = postrepository.findById(id);
        if(p.isPresent()){
            post.setId(id);
            postrepository.save(modelMapper.map(post, Post.class));
        }
    }

    @Override
    public PostDto getById(Long id) {
        Post p = postrepository.findById(id).orElse(null);
        return modelMapper.map(p, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByAuthor(String author) {
        List<Post> p = postrepository.findAll().stream()
                .filter(e -> e.getAuthor().contains(author))
                .collect(Collectors.toList());
        return listMapper.mapList(p, new PostDto());
    }
}
