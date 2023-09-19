package com.backend.blog.Blog.api.App.Repositries;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.blog.Blog.api.App.Entities.Categories;
import com.backend.blog.Blog.api.App.Entities.Posts;
import com.backend.blog.Blog.api.App.Entities.User;

public interface Post_repo extends JpaRepository<Posts,Integer> {
    
    Page<Posts> findByUser (User user, Pageable pageable);
    Page<Posts> findByCategory (Categories category, Pageable pageable);

    Page<Posts> findByTitleContainingOrContentContaining (String titleKeyword, String contentKeyword, Pageable pageable);
    
}
