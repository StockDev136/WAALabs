package com.waa.waaproject.service.implementation;

import com.waa.waaproject.service.ICommentService;
import com.waa.waaproject.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService implements ICommentService {
    public final IPostService postService;

    @Autowired
    public CommentService(IPostService postService) {
        this.postService = postService;
    }
}
