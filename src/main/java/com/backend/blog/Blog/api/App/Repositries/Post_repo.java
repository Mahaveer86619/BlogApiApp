package com.backend.blog.Blog.api.App.Repositries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.blog.Blog.api.App.Entities.Categories;
import com.backend.blog.Blog.api.App.Entities.Posts;
import com.backend.blog.Blog.api.App.Entities.User;

public interface Post_repo extends JpaRepository<Posts,Integer> {
    
    List<Posts> findByUser (User user);
    List<Posts> findByCategory (Categories category);
    
}
