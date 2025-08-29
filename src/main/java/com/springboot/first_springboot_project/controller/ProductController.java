package com.springboot.first_springboot_project.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.first_springboot_project.dto.request.ProductRequest;
import com.springboot.first_springboot_project.dto.response.ApiResponse;
import com.springboot.first_springboot_project.dto.response.ProductResponse;
import com.springboot.first_springboot_project.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts(){
        return ResponseEntity.ok(new ApiResponse<>("success", "Daftar Product Ditemukan", productService.getAllProducts()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable UUID id){
        return ResponseEntity.ok(new ApiResponse<>("success", "Product Ditemukan", productService.getProductById(id)));
    }
 
    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> addProduct(@Valid @RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.addProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("success", "Product baru berhasil ditambahkan", productResponse));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProductById(@PathVariable UUID id, @RequestBody ProductRequest productRequest){
        ProductResponse response = productService.updateProduct(id, productRequest);
        return ResponseEntity.ok(new ApiResponse<>("success", "Data berhasil di update", response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProductById(@PathVariable UUID id){
        productService.deleteProduct(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Product berhasil dihapus", null));
    }


}
