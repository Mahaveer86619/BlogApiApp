package com.backend.blog.Blog.api.App.Services;

import com.backend.blog.Blog.api.App.Payloads.Post_dto;
import com.backend.blog.Blog.api.App.Payloads.Post_response;

public interface Post_service {
    
    //create
    Post_dto createPost(Post_dto post_dto, Integer userid, Integer categoryid);

    //update
    Post_dto updatePosts(Post_dto post_dto, Integer postid);

    //delete
    void deletePost(Integer postid);

    //All posts
    Post_response getAllPosts (Integer pageNumber, Integer pageSize, String sortby, String sortDir);

    //get single post
    Post_dto getPostById (Integer postid);

    // get post by caegory
    Post_response getPostsByCategory (Integer categoryid, Integer pageNumber, Integer pageSize, String sortby, String sortDir);

    //get post by user
    Post_response getPostsByUser (Integer userid, Integer pageNumber, Integer pageSize, String sortby, String sortDir);

    // get post by search
    Post_response getPostsBySearch (String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

}
