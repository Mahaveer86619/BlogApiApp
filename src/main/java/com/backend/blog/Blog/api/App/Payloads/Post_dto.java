package com.backend.blog.Blog.api.App.Payloads;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Post_dto {

    private Integer postId;
    @NotBlank
    @Size(min = 4, message = "Min size should be more than 4 characters.")
    private String title;
    @NotBlank
    @Size(min = 10, message = "Min size should be more than 10 characters.")
    private String content;
    //changed int to string 
    private String imageName;
    private Date dateAdded;
    private Category_dto category;
    private User_dto user;

}
