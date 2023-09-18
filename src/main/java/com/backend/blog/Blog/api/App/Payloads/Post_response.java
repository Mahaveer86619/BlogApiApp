package com.backend.blog.Blog.api.App.Payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post_response {
    
    private List<Post_dto> content;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;

    private boolean lastPage;
}
