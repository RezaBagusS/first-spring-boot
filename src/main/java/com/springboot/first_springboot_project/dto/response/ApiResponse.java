package com.springboot.first_springboot_project.dto.response;

public record ApiResponse<T>(String status, String message, T data) {}
