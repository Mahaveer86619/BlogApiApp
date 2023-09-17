package com.backend.blog.Blog.api.App.Entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Posts")
@NoArgsConstructor
@Getter
@Setter
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "Title", length = 50)
    private String title;

    @Column(name = "Content", length = 1000)
    private String content;

    @Column(name = "Image", length = 300)
    private Integer imageName;

    @Column(name = "Date Added", length = 10)
    private Date dateAdded;
    

    // relations
    @ManyToOne
    private Categories category;
    @ManyToOne
    private User user;
}
