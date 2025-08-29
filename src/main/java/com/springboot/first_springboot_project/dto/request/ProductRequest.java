package com.springboot.first_springboot_project.dto.request;

import java.math.BigDecimal;

public record ProductRequest(
    String name, 
    BigDecimal price, 
    Integer stock
) {}
