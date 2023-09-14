package com.backend.blog.Blog.api.App.Repositries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.blog.Blog.api.App.Entities.User;

public interface User_repo extends JpaRepository<User, Integer> {

}
