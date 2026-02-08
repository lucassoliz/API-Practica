package com.exampleLoadBalancing.users_service.controller;

import com.exampleLoadBalancing.users_service.dto.UserDTO;
import com.exampleLoadBalancing.users_service.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public IUserService userService;

    @GetMapping("/posts/{user_id}")
    public UserDTO getUserAndPosts(@PathVariable Long user_id) {

        UserDTO user = userService.getUserAndPosts(user_id);
        return user;
    }

}
