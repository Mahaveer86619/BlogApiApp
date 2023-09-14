package com.backend.blog.Blog.api.App.Payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class User_dto {

    
    private int id;

    @NotEmpty
    @Size(min = 4,message = "Username must be greter than 4 characters !!")
    private String name;

    @Email(message = "Your email address is not valid !!")
    private String email;

    @NotEmpty
    @Size(min = 4, max = 10, message = "Password must be between 4-10 characters")
    private String password;
    
    @Size(max = 50, message = "About should not exceed 50 characters")
    private String about; 
}
