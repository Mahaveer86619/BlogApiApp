package com.backend.blog.Blog.api.App.Services;

import java.util.List;

import com.backend.blog.Blog.api.App.Payloads.Post_dto;

public interface Post_service {
    
    //create
    Post_dto createPost(Post_dto post_dto, Integer userid, Integer categoryid);

    //update
    Post_dto updatePosts(Post_dto post_dto, Integer postid);

    //delete
    void deletePost(Integer postid);

    //All posts
    List<Post_dto> getAllPosts ();

    //get single post
    Post_dto getPostById (Integer postid);

    // get post by caegory
    List<Post_dto> getPostsByCategory (Integer categoryid);

    //get post by user
    List<Post_dto> getPostsByUser (Integer userid);

    // get post by search
    Post_dto getPostsBySearch (String keyword);

}
