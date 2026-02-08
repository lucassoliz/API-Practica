package com.exampleLoadBalancing.users_service.service;

import com.exampleLoadBalancing.users_service.dto.UserDTO;

public interface IUserService {

    public UserDTO getUserAndPosts(Long user_id);


}
