package com.backend.blog.Blog.api.App.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Contact_us_dto {
    
    private int id;
    private String name;
    private String email;
    private String contact_no;
    private String content;

}
