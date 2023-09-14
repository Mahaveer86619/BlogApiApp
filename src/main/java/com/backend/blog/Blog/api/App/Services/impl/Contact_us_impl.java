package com.backend.blog.Blog.api.App.Services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.blog.Blog.api.App.Entities.Contact_us;
import com.backend.blog.Blog.api.App.Exceptions.ResourceNotFoundException;
import com.backend.blog.Blog.api.App.Payloads.Contact_us_dto;
import com.backend.blog.Blog.api.App.Repositries.Contact_us_repo;
import com.backend.blog.Blog.api.App.Services.Contact_us_service;

@Service
public class Contact_us_impl implements Contact_us_service{

    @Autowired
    private Contact_us_repo contact_us_repo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Contact_us_dto create(Contact_us_dto contact_us_dto) {

        Contact_us contactus = this.dtotoentity(contact_us_dto);
        Contact_us saved = this.contact_us_repo.save(contactus);

        return this.entitytodto(saved);
    }

    @Override
    public Contact_us_dto get_user_by_id(Integer id) {

        Contact_us contact_us = this.contact_us_repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact_us", "id", id));

        return this.entitytodto(contact_us);
    }

    @Override
    public List<Contact_us_dto> get_all() {

        List<Contact_us> many_Contact_us = this.contact_us_repo.findAll();
        List<Contact_us_dto> contact_us_dtos = many_Contact_us.stream().map(contact_us -> this.entitytodto(contact_us)).collect(Collectors.toList());

        return contact_us_dtos;
    }

    @Override
    public void delete(Integer id) {
        Contact_us contact_us = this.contact_us_repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact_us", "id", id));

        this.contact_us_repo.delete(contact_us);
    }


    
    public Contact_us dtotoentity (Contact_us_dto contact_us_dto) {

        Contact_us contact_us = this.modelMapper.map(contact_us_dto, Contact_us.class);

        return contact_us;
    }

    public Contact_us_dto entitytodto (Contact_us contact_us) {
        
        Contact_us_dto contact_us_dto = this.modelMapper.map(contact_us, Contact_us_dto.class);

        return contact_us_dto;
    }
    
}
