package com.backend.blog.Blog.api.App.Controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.blog.Blog.api.App.Payloads.Api_response;
import com.backend.blog.Blog.api.App.Payloads.Category_dto;
import com.backend.blog.Blog.api.App.Services.Category_service;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/categories")
public class Category_controler {


    @Autowired
    private Category_service category_service ;

    @PostMapping("/")
    public ResponseEntity<Category_dto> create_Category(@Valid@RequestBody Category_dto category_dto){
        Category_dto created_category = this.category_service.createCategory(category_dto);
        return new ResponseEntity<Category_dto>(created_category, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryid}")
    public ResponseEntity<Category_dto> update_Category(@Valid@RequestBody Category_dto category_dto , @PathVariable("categoryid") Integer categoryId){
        Category_dto updated_category = this.category_service.updateCategory(category_dto , categoryId);
        return ResponseEntity.ok(updated_category);
    }
    
    @DeleteMapping("/{categoryid}")
    public ResponseEntity<Api_response> delete_Category(@PathVariable Integer categoryid) {
        this.category_service.deleteCategory(categoryid);
        return new ResponseEntity<Api_response>
        (new Api_response("Category deleted succesfully", true), HttpStatus.OK);
    }
    
    @GetMapping(("/"))
    public ResponseEntity<List<Category_dto>> get_All_Category() {
        return ResponseEntity.ok(this.category_service.getAllCategory());
    }
    
    @GetMapping(("/{categoryid}"))
    public ResponseEntity<Category_dto> get_Single_Category(@PathVariable Integer categoryid) {
        Category_dto category_dto = this.category_service.getCategory(categoryid);
        return new ResponseEntity<Category_dto>(category_dto, HttpStatus.OK);
    }
}