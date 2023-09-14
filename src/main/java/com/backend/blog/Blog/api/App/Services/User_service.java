package com.backend.blog.Blog.api.App.Services;

import java.util.List;

import com.backend.blog.Blog.api.App.Payloads.User_dto;

public interface User_service {

    User_dto createUser(User_dto user);

    User_dto updateUser(User_dto user, Integer userId);

    User_dto getUserById(Integer id);

    List<User_dto> getAllUsers();

    void deleteUser(Integer id);
}