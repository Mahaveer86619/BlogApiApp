package com.backend.blog.Blog.api.App.Services.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.blog.Blog.api.App.Entities.Categories;
import com.backend.blog.Blog.api.App.Exceptions.ResourceNotFoundException;
import com.backend.blog.Blog.api.App.Payloads.Category_dto;
import com.backend.blog.Blog.api.App.Repositries.Category_repo;
import com.backend.blog.Blog.api.App.Services.Category_service;

@Service
public class Category_service_impl implements Category_service {

    @Autowired
    public Category_repo category_repo;

    @Autowired 
    private ModelMapper modelMapper;

    @Override
    public Category_dto createCategory(Category_dto category_dto) {

        Categories categories = this.modelMapper.map(category_dto, Categories.class);
        Categories saved_cat = this.category_repo.save(categories);

        return this.modelMapper.map(saved_cat, Category_dto.class);

    }

    @Override
    public Category_dto updateCategory(Category_dto category_dto, Integer categoryId) {
        
        Categories categories = this.category_repo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id ", categoryId));
        
        categories.setCategoryId(category_dto.getCategoryId());
        categories.setCategoryTitle(category_dto.getCategoryTitle());
        categories.setCategorydescription(category_dto.getCategoryDescription());

        Categories update_cat = this.category_repo.save(categories);
        
        return this.modelMapper.map(update_cat, Category_dto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Categories categories = this.category_repo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category ", "CAtegory Id ", categoryId));

        this.category_repo.delete(categories);
    }

    @Override
    public Category_dto getCategory(Integer categoryId) {
        Categories categories = this.category_repo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category ", "CAtegory Id ", categoryId));
        
        return this.modelMapper.map(categories, Category_dto.class);
    }

    @Override
    public List<Category_dto> getAllCategory() {
        List<Categories> categories = this.category_repo.findAll();
        List<Category_dto> category_dtos = categories.stream().map((cat) -> this.modelMapper.map(cat, Category_dto.class)).collect(Collectors.toList());
        
        return category_dtos;
    }

}
