package com.backend.blog.Blog.api.App.Services;

import java.util.List;

import com.backend.blog.Blog.api.App.Payloads.Category_dto;

public interface Category_service { 
    
    //create
    Category_dto createCategory(Category_dto category_dto);
    //update
    Category_dto updateCategory(Category_dto category_dto , Integer categoryId);
    //delete
    void deleteCategory(Integer categoryId);
    //get one
    Category_dto getCategory(Integer categoryId);
    //getall
    List<Category_dto> getAllCategory();
    
}
