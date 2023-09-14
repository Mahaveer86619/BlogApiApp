package com.backend.blog.Blog.api.App.Payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Category_dto { 
    
    private Integer categoryId;
    @NotBlank
    @Size(min = 4, message = "Min size should be more than 4 characters.")
    private String categoryTitle;
    @NotBlank
    @Size(min = 10, message = "Min size of description should be more than 10 characters.")
    private String categoryDescription; 
    
}
