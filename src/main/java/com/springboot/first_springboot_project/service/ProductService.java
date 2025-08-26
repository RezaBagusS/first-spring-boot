package com.springboot.first_springboot_project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.first_springboot_project.dto.response.ProductResponse;
import com.springboot.first_springboot_project.entity.Product;

@Service
public class ProductService {
    
    private final List<Product> products = new ArrayList<>();

    public List<ProductResponse> getAllProducts(){
        return products.stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock()))
                .toList();
    }

}
