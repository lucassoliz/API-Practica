package com.exampleLoadBalancing.users_service.service;

import com.exampleLoadBalancing.users_service.dto.PostDTO;
import com.exampleLoadBalancing.users_service.dto.UserDTO;
import com.exampleLoadBalancing.users_service.model.User;
import com.exampleLoadBalancing.users_service.repository.IPostAPI;
import com.exampleLoadBalancing.users_service.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements IUserService{

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private IPostAPI apiPosts;

    @Override
    public UserDTO getUserAndPosts(Long user_id) {

        //Datos del usuario de la BD
        User user = userRepo.findById(user_id).orElse(null);

        //Una vez que tengo los datos necesito los POSTEOS


        //Traigo posteos desde la API de posteos
        List<PostDTO> postsList = apiPosts.getPostsByUserId(user_id);


        //Unificar datos del usuario + Posteos

        //User DTO
        UserDTO userDTO = new UserDTO(user.getUser_id(), user.getName(),
                user.getLastname(), user.getCellphone(), postsList);


        return userDTO;
    }
}
