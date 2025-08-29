package com.springboot.first_springboot_project.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.first_springboot_project.dto.request.ProductRequest;
import com.springboot.first_springboot_project.dto.response.ProductResponse;
import com.springboot.first_springboot_project.entity.Product;
import com.springboot.first_springboot_project.exception.NotFoundException;
import com.springboot.first_springboot_project.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    // Constructor Injection
    public ProductService(ProductRepository productRepository) {
        // Spring akan otomatis menyediakan instance ProductRepository ke constructor ini
        this.productRepository = productRepository;
    }

    private Product findProductById(UUID id) {
        return productRepository.findById(id).get();
    }

    private void updateNameIfPresent(Product product, String name) {
        if (name != null) {
            product.setName(name);
        }
    }

    private void updatePriceIfPresent(Product product, BigDecimal price) {
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
        return productRepository.findAll().stream()
                .map(product -> new ProductResponse(product.getId(), product.getName(), product.getPrice(),
                        product.getStock()))
                .toList();
    }

    public ProductResponse getProductById(UUID id) {
        Product product = findProductById(id);
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock());
    }

    public ProductResponse addProduct(ProductRequest productRequest) {

        if (productRequest.price().intValue() < 0 || productRequest.price() == null) {
            throw new IllegalArgumentException("Price harus lebih dari atau sama dengan 0");
        }

        Product product = new Product(productRequest.name(), productRequest.price(), 0);
        productRepository.save(product);
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock());
    }

    @Transactional
    public ProductResponse updateProduct(UUID id, ProductRequest productRequest) {
        Product product = findProductById(id);

        updateNameIfPresent(product, productRequest.name());
        updatePriceIfPresent(product, productRequest.price());
        updateStockIfPresent(product, productRequest.stock());

        productRepository.save(product);

        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock());
    }

    public void deleteProduct(UUID id){

        boolean exists = productRepository.existsById(id);

        if (!exists) {
            // 3. Jika tidak ada, lempar exception.
            throw new NotFoundException("Produk dengan ID " + id + " gagal dihapus karena tidak ditemukan");
        }

        productRepository.deleteById(id);

    }
}
