package com.backend.blog.Blog.api.App.Controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.blog.Blog.api.App.Payloads.Post_dto;
import com.backend.blog.Blog.api.App.Services.Post_service;


@RestController
@RequestMapping("/api")
public class Post_controler {

    @Autowired
    private Post_service post_service;

    // create
    @PostMapping("/user/{userid}/category/{categoryid}/posts")
    public ResponseEntity<Post_dto> createPost(@RequestBody Post_dto post_dto,
            @PathVariable("userid") Integer userid,
            @PathVariable("categoryid") Integer categoryid) 
    {
        Post_dto createPost = this.post_service.createPost(post_dto, userid, categoryid);
        return new ResponseEntity<Post_dto>(createPost, HttpStatus.CREATED);
    }

    // get all posts from userid
    @GetMapping("/user/{userid}/posts")
    public ResponseEntity<List<Post_dto>> getPostsByUserId (@PathVariable("userid") Integer userid) {

        List<Post_dto> posts = this.post_service.getPostsByUser(userid);

        return new ResponseEntity<List<Post_dto>>(posts, HttpStatus.OK);
    }

    // get all posts from categoryid
    @GetMapping("/category/{categoryid}/posts")
    public ResponseEntity<List<Post_dto>> getPostsByCategoryId (@PathVariable("categoryid") Integer categoryid) {

        List<Post_dto> posts = this.post_service.getPostsByUser(categoryid);

        return new ResponseEntity<List<Post_dto>>(posts, HttpStatus.OK);
    }

}
