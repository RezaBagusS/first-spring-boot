package com.springboot.first_springboot_project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.first_springboot_project.dto.request.ProductRequest;
import com.springboot.first_springboot_project.dto.response.ProductResponse;
import com.springboot.first_springboot_project.entity.Product;
import com.springboot.first_springboot_project.exception.NotFoundException;

@Service
public class ProductService {
    
    private final List<Product> products = new ArrayList<>();
    private Long nextId = 1L;

    public List<ProductResponse> getAllProducts(){
        return products.stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock()))
                .toList();
    }

    public ProductResponse getProductById(Long id){
        Product product = products.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Product dengan ID " + id + " tidak ditemukan"));
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock());
    }

    public ProductResponse addProduct(ProductRequest productRequest){
        Product product = new Product(nextId++, productRequest.name(), productRequest.price(), 0);
        products.add(product);
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock());
    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest){
        Product product = products.stream()
                            .filter(item -> item.getId().equals(id))
                            .findFirst()
                            .orElseThrow(() -> new NotFoundException("Data Product tidak ditemukan untuk ID " + id));
        product.setName(productRequest.name() != null ? productRequest.name() : product.getName());
        product.setPrice(productRequest.price() != 0.0 ? productRequest.price() : product.getPrice());
        product.setStock(productRequest.stock() != 0 ? productRequest.stock() : product.getStock());
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock());
    }
}
