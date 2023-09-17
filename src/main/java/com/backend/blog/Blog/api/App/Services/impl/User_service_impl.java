package com.backend.blog.Blog.api.App.Services.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.blog.Blog.api.App.Entities.User;
import com.backend.blog.Blog.api.App.Exceptions.ResourceNotFoundException;
import com.backend.blog.Blog.api.App.Payloads.User_dto;
import com.backend.blog.Blog.api.App.Repositries.User_repo;
import com.backend.blog.Blog.api.App.Services.User_service;

@Service
public class User_service_impl implements User_service {

    @Autowired
    private User_repo user_repo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User_dto createUser(User_dto user_dto) {

        User user = this.DTOtoUser(user_dto);
        User saved_user = this.user_repo.save(user);

        return this.UsertoDTO(saved_user);
    }

    @Override
    public User_dto updateUser(User_dto user_dto, Integer userId) {

        User user = this.user_repo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        user.setId(user_dto.getId());
        user.setName(user_dto.getName());
        user.setEmail(user_dto.getEmail());
        user.setPassword(user_dto.getPassword());

        User updated_user = this.user_repo.save(user);
        User_dto user_dto2 = this.UsertoDTO(updated_user);

        return user_dto2;
    }

    @Override
    public User_dto getUserById(Integer userid) {

        User user = this.user_repo.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userid));

        return this.UsertoDTO(user);
    }

    @Override
    public List<User_dto> getAllUsers() {

        List<User> users = this.user_repo.findAll();
        List<User_dto> user_dtos = users.stream().map(user -> this.UsertoDTO(user)).collect(Collectors.toList());

        return user_dtos;
    }

    @Override
    public void deleteUser(Integer userid) {

        User user = this.user_repo.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userid));
        this.user_repo.delete(user);
    }

    public User DTOtoUser(User_dto user_dto) {

        User user = this.modelMapper.map(user_dto, User.class);

        // User user = new User();
        // user.setId(user_dto.getId());
        // user.setName(user_dto.getName());
        // user.setAbout(user_dto.getAbout());
        // user.setEmail(user_dto.getEmail());
        // user.setPassword(user_dto.getPassword());
        return user;
    }

    public User_dto UsertoDTO(User user) {

        User_dto user_dto = this.modelMapper.map(user, User_dto.class);

        // User_dto user_dto = new User_dto();
        // user_dto.setId(user.getId());
        // user_dto.setName(user.getName());
        // user_dto.setAbout(user.getAbout());
        // user_dto.setEmail(user.getEmail());
        // user_dto.setPassword(user.getPassword());
        return user_dto;
    }

}
