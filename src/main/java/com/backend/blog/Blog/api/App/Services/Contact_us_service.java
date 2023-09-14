package com.backend.blog.Blog.api.App.Services;

import java.util.List;

import com.backend.blog.Blog.api.App.Payloads.Contact_us_dto;

public interface Contact_us_service {
    
    Contact_us_dto create (Contact_us_dto contact_us_dto);
    Contact_us_dto get_user_by_id (Integer id);
    List<Contact_us_dto> get_all ();
    void delete (Integer id);
}
