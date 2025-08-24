package com.springboot.first_springboot_project.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TaskRequest(
        @NotBlank(message = "Title tidak boleh kosong")
        String title
) {}