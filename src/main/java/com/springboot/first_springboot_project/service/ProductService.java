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

    private Product findProductById(Long id) {
        Product product = products.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Data Product tidak ditemukan untuk ID " + id));
        return product;
    }

    private void updateNameIfPresent(Product product, String name) {
        if (name != null) {
            product.setName(name);
        }
    }

    private void updatePriceIfPresent(Product product, Double price) {
        if (price != null) {
            validateNonNegative(price, "Price");
            product.setPrice(price);
        }
    }

    private void updateStockIfPresent(Product product, Integer stock) {
        if (stock != null) {
            validateNonNegative(stock, "Stock");
            product.setStock(stock);
        }
    }

    private void validateNonNegative(Number value, String fieldName) {
        if (value.doubleValue() < 0) {
            throw new IllegalArgumentException(fieldName + " harus lebih dari atau sama dengan 0");
        }
    }

    public List<ProductResponse> getAllProducts() {
        return products.stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getPrice(),
                        product.getStock()))
                .toList();
    }

    public ProductResponse getProductById(Long id) {
        Product product = products.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Product dengan ID " + id + " tidak ditemukan"));
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock());
    }

    public ProductResponse addProduct(ProductRequest productRequest) {

        if (productRequest.price() < 0 || productRequest.price() == null) {
            throw new IllegalArgumentException("Price harus lebih dari atau sama dengan 0");
        }

        Product product = new Product(nextId++, productRequest.name(), productRequest.price(), 0);
        products.add(product);
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock());
    }

    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
        Product product = findProductById(id);

        updateNameIfPresent(product, productRequest.name());
        updatePriceIfPresent(product, productRequest.price());
        updateStockIfPresent(product, productRequest.stock());

        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock());
    }

    public void deleteProduct(Long id){
        boolean removed = products.removeIf(item -> item.getId().equals(id));

        if (!removed) {
            throw new NotFoundException("Data Product dengan ID " + id + " tidak ditemukan");
        }
    }
}
