package com.springboot.first_springboot_project.dto.request;

public record ProductRequest(
    String name, 
    Double price, 
    Integer stock
) {}
