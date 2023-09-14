package com.backend.blog.Blog.api.App.Repositries;

import com.backend.blog.Blog.api.App.Entities.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Category_repo extends JpaRepository<Categories, Integer>{
    
}
