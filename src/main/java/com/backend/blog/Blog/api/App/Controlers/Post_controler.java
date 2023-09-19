package com.backend.blog.Blog.api.App.Controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.blog.Blog.api.App.Config.App_constants;
import com.backend.blog.Blog.api.App.Payloads.Api_response;
import com.backend.blog.Blog.api.App.Payloads.Post_dto;
import com.backend.blog.Blog.api.App.Payloads.Post_response;
import com.backend.blog.Blog.api.App.Services.Post_service;

@RestController
@RequestMapping("/api")
public class Post_controler {

    @Autowired
    private Post_service post_service;

    @Value("${project.image}")
    private String path;

    // create
    @PostMapping("/user/{userid}/category/{categoryid}/posts")
    public ResponseEntity<Post_dto> createPost(@RequestBody Post_dto post_dto,
            @PathVariable Integer userid,
            @PathVariable Integer categoryid) {
        Post_dto createPost = this.post_service.createPost(post_dto, userid, categoryid);
        return new ResponseEntity<Post_dto>(createPost, HttpStatus.CREATED);
    }

    // get all posts from userid
    @GetMapping("/user/{userid}/posts")
    public ResponseEntity<Post_response> getPostsByUserId(
            @PathVariable Integer userid,
            @RequestParam(defaultValue = App_constants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = App_constants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(defaultValue = App_constants.SORT_BY, required = false) String sortBy,
            @RequestParam(defaultValue = App_constants.SORT_DIR, required = false) String sortDir) {

        Post_response postrResponse = this.post_service.getPostsByUser(userid, pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<Post_response>(postrResponse, HttpStatus.OK);
    }

    // get all posts from categoryid
    @GetMapping("/category/{categoryid}/posts")
    public ResponseEntity<Post_response> getPostsByCategoryId(
            @PathVariable Integer categoryid,
            @RequestParam(defaultValue = App_constants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = App_constants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(defaultValue = App_constants.SORT_BY, required = false) String sortBy,
            @RequestParam(defaultValue = App_constants.SORT_DIR, required = false) String sortDir) {

        Post_response postrResponse = this.post_service.getPostsByCategory(categoryid, pageNumber, pageSize, sortBy,
                sortDir);

        return new ResponseEntity<Post_response>(postrResponse, HttpStatus.OK);
    }

    // get all posts and sort and implement pagination page size starts form 0
    // http://localhost:6969/api/posts?pageNumber=0&pageSize=2&sortDir=asc or desc
    // (example)
    //
    @GetMapping("/posts")
    public ResponseEntity<Post_response> getAllPosts(
            @RequestParam(defaultValue = App_constants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = App_constants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(defaultValue = App_constants.SORT_BY, required = false) String sortBy,
            @RequestParam(defaultValue = App_constants.SORT_DIR, required = false) String sortDir) {

        Post_response postrResponse = this.post_service.getAllPosts(pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<Post_response>(postrResponse, HttpStatus.OK);
    }

    // get post by Id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post_dto> getPostById(@PathVariable Integer postId) {

        Post_dto post_dto = this.post_service.getPostById(postId);

        return new ResponseEntity<Post_dto>(post_dto, HttpStatus.OK);
    }

    // delete post
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Api_response> deletePost(@PathVariable Integer postId) {
        this.post_service.deletePost(postId);

        return new ResponseEntity<Api_response>(new Api_response("Post deleted succesfully", true), HttpStatus.OK);

    }

    // update post
    @PutMapping("/posts/{postId}")
    public ResponseEntity<Post_dto> updatepost(@RequestBody Post_dto post_dto, @PathVariable Integer postId) {

        Post_dto post = this.post_service.updatePosts(post_dto, postId);

        return new ResponseEntity<Post_dto>(post, HttpStatus.OK);

    }

    // search a keyword for any relevant title or content
    @GetMapping("/posts/search")
    public ResponseEntity<Post_response> searchPosts(
            @RequestParam String keyword,
            @RequestParam(defaultValue = App_constants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = App_constants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(defaultValue = App_constants.SORT_BY, required = false) String sortBy,
            @RequestParam(defaultValue = App_constants.SORT_DIR, required = false) String sortDir) {
        Post_response postResponse = this.post_service.getPostsBySearch(keyword, pageNumber, pageSize, sortBy, sortDir);

        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

}
