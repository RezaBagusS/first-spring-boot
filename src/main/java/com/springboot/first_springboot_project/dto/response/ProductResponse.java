package com.springboot.first_springboot_project.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(UUID id, String name, BigDecimal price, Integer stock ) {}

