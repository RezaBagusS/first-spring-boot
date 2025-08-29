package com.springboot.first_springboot_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot.first_springboot_project.entity.Product;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    // Tetap kosong, semua fungsionalitas CRUD, pagination, dan sorting sudah tersedia.
}
