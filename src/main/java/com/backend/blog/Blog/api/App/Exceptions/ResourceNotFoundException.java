package com.backend.blog.Blog.api.App.Exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    String resourse_name;
    String field_name;
    long field_value;
    

    public ResourceNotFoundException(String resourse_name, String field_name, long field_value) {
        super(String.format("%s not found with %s : %s", resourse_name, field_name, field_value));
        this.resourse_name = resourse_name;
        this.field_name = field_name;
        this.field_value = field_value;
    }
}
