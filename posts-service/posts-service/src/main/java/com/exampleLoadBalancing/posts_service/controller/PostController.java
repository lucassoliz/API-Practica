package com.exampleLoadBalancing.posts_service.controller;

import com.exampleLoadBalancing.posts_service.model.Post;
import com.exampleLoadBalancing.posts_service.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private IPostService postService;

    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/{user_id}")
    public List<Post> getPostsByUserId(@PathVariable Long user_id) {
        System.out.println("----------- Estoy en el puerto: " + serverPort);
        return postService.getPostByUser(user_id);
    }

}
