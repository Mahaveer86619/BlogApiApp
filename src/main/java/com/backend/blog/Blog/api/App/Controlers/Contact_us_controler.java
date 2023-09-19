package com.backend.blog.Blog.api.App.Controlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.blog.Blog.api.App.Payloads.Api_response;
import com.backend.blog.Blog.api.App.Payloads.Contact_us_dto;
import com.backend.blog.Blog.api.App.Services.Contact_us_service;

@RestController
@RequestMapping("/api/ContactUs")
public class Contact_us_controler {

    @Autowired
    private Contact_us_service contact_us_service;

    // post create
    @PostMapping("/create")
    public ResponseEntity<Contact_us_dto> create(@RequestBody Contact_us_dto contact_us_dto) {

        Contact_us_dto create = this.contact_us_service.create(contact_us_dto);
        return new ResponseEntity<>(create, HttpStatus.CREATED);
    }

    // get find one
    @GetMapping(("/{userid}"))
    public ResponseEntity<Contact_us_dto> get_Single_Users(@PathVariable Integer id) {

        return ResponseEntity.ok(this.contact_us_service.get_user_by_id(id));
    }

    // getall find all
    @GetMapping(("/showAll"))
    public ResponseEntity<List<Contact_us_dto>> get_All_Users() {

        return ResponseEntity.ok(this.contact_us_service.get_all());
    }

    // delete delete one
    @DeleteMapping("/{userid}")
    public ResponseEntity<Api_response> delete_User(@PathVariable Integer id) {
        this.contact_us_service.delete(id);
        return new ResponseEntity<Api_response>(new Api_response("User deleted succesfully", true), HttpStatus.OK);
    }
}
