package com.exampleLoadBalancing.posts_service.service;

import com.exampleLoadBalancing.posts_service.model.Post;
import com.exampleLoadBalancing.posts_service.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService{

    @Autowired
    private IPostRepository repoPost;

    @Override
    public List<Post> getPostByUser(Long user_id) {
        return repoPost.findPostByUserId(user_id);
    }
}
