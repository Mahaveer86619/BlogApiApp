package com.backend.blog.Blog.api.App.Entities;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "User_name", nullable = false, length = 50)
    private String name;

    @Column(name = "User_email", nullable = false, length = 50)
    private String email;

    @Column(name = "User_pass", nullable = false, length = 20)
    private String password;

    @Column(name = "User_about", nullable = false, length = 100)
    private String about;


    //relations
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Posts> posts = new ArrayList<>();

}
