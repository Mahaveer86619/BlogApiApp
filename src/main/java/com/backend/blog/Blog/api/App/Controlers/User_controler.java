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
import com.backend.blog.Blog.api.App.Payloads.User_dto;
import com.backend.blog.Blog.api.App.Services.User_service;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class User_controler {

    @Autowired
    private User_service user_service;

    @PostMapping("/")
    public ResponseEntity<User_dto> create_User(@Valid @RequestBody User_dto user_dto) {
        User_dto create_user_dto = this.user_service.createUser(user_dto);
        return new ResponseEntity<>(create_user_dto, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User_dto> update_User(@Valid @RequestBody User_dto user_dto,
            @PathVariable("userid") Integer userid) {
        User_dto update_user_dto = this.user_service.updateUser(user_dto, userid);
        return ResponseEntity.ok(update_user_dto);
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<Api_response> delete_User(@Valid @PathVariable("userid") Integer userid) {
        this.user_service.deleteUser(userid);
        return new ResponseEntity<Api_response>(new Api_response("User deleted succesfully", true), HttpStatus.OK);
    }

    @GetMapping(("/"))
    public ResponseEntity<List<User_dto>> get_All_Users() {
        return ResponseEntity.ok(this.user_service.getAllUsers());
    }

    @GetMapping(("/{userid}"))
    public ResponseEntity<User_dto> get_Single_Users(@Valid @PathVariable("userid") Integer userid) {
        return ResponseEntity.ok(this.user_service.getUserById(userid));
    }

}
