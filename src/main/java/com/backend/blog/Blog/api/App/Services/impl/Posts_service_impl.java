package com.backend.blog.Blog.api.App.Services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.blog.Blog.api.App.Entities.Categories;
import com.backend.blog.Blog.api.App.Entities.Posts;
import com.backend.blog.Blog.api.App.Entities.User;
import com.backend.blog.Blog.api.App.Exceptions.ResourceNotFoundException;
import com.backend.blog.Blog.api.App.Payloads.Post_dto;
import com.backend.blog.Blog.api.App.Repositries.Category_repo;
import com.backend.blog.Blog.api.App.Repositries.Post_repo;
import com.backend.blog.Blog.api.App.Repositries.User_repo;
import com.backend.blog.Blog.api.App.Services.Post_service;

@Service
public class Posts_service_impl implements Post_service{

    @Autowired
    private Post_repo Post_repo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private User_repo user_repo;

    @Autowired
    private Category_repo category_repo;

    @Override
    public Post_dto createPost(Post_dto post_dto, Integer userid, Integer categoryid) {

        User user = this.user_repo.findById(userid).orElseThrow(() -> new ResourceNotFoundException
        ("User", "User Id ", userid));

        Categories category = this.category_repo.findById(categoryid).orElseThrow(() -> new ResourceNotFoundException
        ("Category", "Category id ", categoryid));


        Posts post = this.mapper.map(post_dto, Posts.class);
        
        post.setDateAdded(new Date());
        post.setImageName(null);
        post.setCategory(category);
        post.setUser(user);

        Posts saved_Post = this.Post_repo.save(post);

        return this.mapper.map(saved_Post, Post_dto.class);

    }

    @Override
    public Post_dto updatePosts(Post_dto post_dto, Integer postid) {
        
        Posts mpost = this.Post_repo.findById(postid).orElseThrow(() -> new ResourceNotFoundException
        ("Post", "postId", postid));

        mpost.setContent(post_dto.getContent());
        mpost.setImageName(post_dto.getImageName());
        mpost.setTitle(post_dto.getTitle());

        Posts updatedPost = this.Post_repo.save(mpost);

        return this.mapper.map(updatedPost, Post_dto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Posts post = this.Post_repo.findById(postId).orElseThrow(() -> new ResourceNotFoundException
        ("Post", "postId", postId));

        this.Post_repo.delete(post);   

    }

    @Override
    public List<Post_dto> getAllPosts() {
        
        List<Posts> posts = this.Post_repo.findAll();
        List<Post_dto> post_dtos = posts.stream()
        .map(post -> this.mapper.map(post, Post_dto.class))
        .collect(Collectors.toList());

        return post_dtos;

    }

    @Override
    public Post_dto getPostById(Integer postid) {
        
        Posts post = this.Post_repo.findById(postid).orElseThrow(() -> new ResourceNotFoundException
        ("post", "postId", postid));

        return this.mapper.map(post, Post_dto.class);
    }

    @Override
    public List<Post_dto> getPostsByCategory(Integer categoryid) {

        Categories cat = this.category_repo.findById(categoryid).orElseThrow(() -> new ResourceNotFoundException
        ("Category", "Category id ", categoryid));

        List<Posts> posts = this.Post_repo.findByCategory(cat);

        List<Post_dto> post_dtos = posts.stream()
        .map(post -> this.mapper.map(post, Post_dto.class))// Map each Post entity to Post_dto
        .collect(Collectors.toList());

        return post_dtos;
    }

    @Override
    public List<Post_dto> getPostsByUser(Integer userid) {

        User user = this.user_repo.findById(userid).orElseThrow(() -> new ResourceNotFoundException
        ("User", "User id", userid));

        List<Posts> posts = this.Post_repo.findByUser(user);

        List<Post_dto> post_dtos = posts.stream()
        .map((post) -> this.mapper.map(post, Post_dto.class))
        .collect(Collectors.toList());

        return post_dtos;
    }

    @Override
    public Post_dto getPostsBySearch(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPostsBySearch'");
    }
    
}
