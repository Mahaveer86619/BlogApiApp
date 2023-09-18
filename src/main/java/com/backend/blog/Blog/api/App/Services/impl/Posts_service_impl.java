package com.backend.blog.Blog.api.App.Services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.backend.blog.Blog.api.App.Entities.Categories;
import com.backend.blog.Blog.api.App.Entities.Posts;
import com.backend.blog.Blog.api.App.Entities.User;
import com.backend.blog.Blog.api.App.Exceptions.ResourceNotFoundException;
import com.backend.blog.Blog.api.App.Payloads.Post_dto;
import com.backend.blog.Blog.api.App.Payloads.Post_response;
import com.backend.blog.Blog.api.App.Repositries.Category_repo;
import com.backend.blog.Blog.api.App.Repositries.Post_repo;
import com.backend.blog.Blog.api.App.Repositries.User_repo;
import com.backend.blog.Blog.api.App.Services.Post_service;

@Service
public class Posts_service_impl implements Post_service {

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

        User user = this.user_repo.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User Id ", userid));

        Categories category = this.category_repo.findById(categoryid)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category id ", categoryid));

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

        Posts mpost = this.Post_repo.findById(postid)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postid));

        mpost.setContent(post_dto.getContent());
        mpost.setImageName(post_dto.getImageName());
        mpost.setTitle(post_dto.getTitle());

        Posts updatedPost = this.Post_repo.save(mpost);

        return this.mapper.map(updatedPost, Post_dto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Posts post = this.Post_repo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));

        this.Post_repo.delete(post);

    }

    // page size starts from 0

    @Override
    public Post_response getAllPosts(Integer pageNumber, Integer pageSize, String sortby, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? sort = Sort.by(sortby).ascending() : Sort.by(sortby).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<Posts> pagePosts = this.Post_repo.findAll(p);

        List<Posts> posts = pagePosts.getContent();

        List<Post_dto> post_dtos = posts.stream()
                .map(post -> this.mapper.map(post, Post_dto.class))
                .collect(Collectors.toList());

        Post_response post_response = new Post_response();

        post_response.setContent(post_dtos);

        post_response.setPageNumber(pagePosts.getNumber());
        post_response.setPageSize(pagePosts.getSize());
        post_response.setTotalElements(pagePosts.getTotalElements());
        post_response.setTotalPages(pagePosts.getTotalPages());
        post_response.setLastPage(pagePosts.isLast());

        return post_response;

    }

    @Override
    public Post_dto getPostById(Integer postid) {

        Posts post = this.Post_repo.findById(postid)
                .orElseThrow(() -> new ResourceNotFoundException("post", "postId", postid));

        return this.mapper.map(post, Post_dto.class);
    }

    @Override
    public Post_response getPostsByCategory(Integer categoryid, Integer pageNumber, Integer pageSize, String sortby,
            String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? sort = Sort.by(sortby).ascending() : Sort.by(sortby).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Categories cat = this.category_repo.findById(categoryid)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category id ", categoryid));

        Page<Posts> pagePosts = this.Post_repo.findByCategory(cat, p);

        List<Posts> posts = pagePosts.getContent();

        List<Post_dto> post_dtos = posts.stream()
                .map(post -> this.mapper.map(post, Post_dto.class))// Map each Post entity to Post_dto
                .collect(Collectors.toList());

        Post_response post_response = new Post_response();

        post_response.setContent(post_dtos);

        post_response.setPageNumber(pagePosts.getNumber());
        post_response.setPageSize(pagePosts.getSize());
        post_response.setTotalElements(pagePosts.getTotalElements());
        post_response.setTotalPages(pagePosts.getTotalPages());
        post_response.setLastPage(pagePosts.isLast());

        return post_response;
    }

    @Override
    public Post_response getPostsByUser(Integer userid, Integer pageNumber, Integer pageSize, String sortby,
            String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? sort = Sort.by(sortby).ascending() : Sort.by(sortby).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        User user = this.user_repo.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User id", userid));

        Page<Posts> pagePosts = this.Post_repo.findByUser(user, p);

        List<Posts> posts = pagePosts.getContent();

        List<Post_dto> post_dtos = posts.stream()
                .map((post) -> this.mapper.map(post, Post_dto.class))
                .collect(Collectors.toList());

        Post_response post_response = new Post_response();

        post_response.setContent(post_dtos);

        post_response.setPageNumber(pagePosts.getNumber());
        post_response.setPageSize(pagePosts.getSize());
        post_response.setTotalElements(pagePosts.getTotalElements());
        post_response.setTotalPages(pagePosts.getTotalPages());
        post_response.setLastPage(pagePosts.isLast());

        return post_response;
    }

    @Override
    public Post_response getPostsBySearch(String keyword, Integer pageNumber, Integer pageSize, String sortBy,
            String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        Page<Posts> pagePosts = this.Post_repo.findByTitleContainingOrContentContaining(keyword, keyword, pageable);

        List<Posts> posts = pagePosts.getContent();

        List<Post_dto> post_dtos = posts.stream()
                .map(post -> this.mapper.map(post, Post_dto.class))
                .collect(Collectors.toList());

        Post_response post_response = new Post_response();
        post_response.setContent(post_dtos);
        post_response.setPageNumber(pagePosts.getNumber());
        post_response.setPageSize(pagePosts.getSize());
        post_response.setTotalElements(pagePosts.getTotalElements());
        post_response.setTotalPages(pagePosts.getTotalPages());
        post_response.setLastPage(pagePosts.isLast());

        return post_response;
    }

}
