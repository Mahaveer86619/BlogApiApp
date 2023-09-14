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
    public Posts updatePosts(Post_dto post_dto, Integer postid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePosts'");
    }

    @Override
    public void deletePost(Integer postid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePost'");
    }

    @Override
    public List<Posts> getAllPosts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllPosts'");
    }

    @Override
    public Posts getPostById(Integer postid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPostById'");
    }

    @Override
    public List<Post_dto> getPostsByCategory(Integer categoryid) {

        Categories cat = this.category_repo.findById(categoryid).orElseThrow(() -> new ResourceNotFoundException
        ("Category", "Category id ", categoryid));

        List<Posts> posts = this.Post_repo.findByCategory(cat);

        List<Post_dto> post_dtos = posts.stream().map((post) -> this.mapper.map(posts, Post_dto.class)).collect(Collectors.toList());

        return post_dtos;
    }

    @Override
    public List<Post_dto> getPostsByUser(Integer userid) {

        User user = this.user_repo.findById(userid).orElseThrow(() -> new ResourceNotFoundException
        ("User", "User id", userid));

        List<Posts> posts = this.Post_repo.findByUser(user);

        List<Post_dto> post_dtos = posts.stream().map((post) -> this.mapper.map(posts, Post_dto.class)).collect(Collectors.toList());

        return post_dtos;
    }

    @Override
    public Posts getPostsBySearch(String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPostsBySearch'");
    }
    
}
