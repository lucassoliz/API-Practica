package com.example.springSecurityBasic.service;

import com.example.springSecurityBasic.model.Role;

import java.util.List;
import java.util.Optional;

//Service interface for Role entity, defining CRUD operations
public interface IRoleService {
    List<Role> findAll();
    Optional<Role> findById(Long id);
    Role save(Role role);
    void deleteById(Long id);
    Role update(Role role);
}
