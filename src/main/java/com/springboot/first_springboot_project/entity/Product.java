package com.springboot.first_springboot_project.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "products") // Nama tabel di database
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Nama product tidak boleh kosong")
    @Column(nullable = false)
    private String name;
    
    @NotNull(message = "Harga tidak boleh kosong")
    @Min(value = 0, message = "Harga tidak boleh negatif")
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull(message = "Stok tidak boleh kosong")
    @Min(value = 0, message = "Stok tidak boleh negatif")
    @Column(nullable = false)
    private Integer stock;

    // JPA membutuhkan constructor kosong
    public Product() {
    }

    // Constructor untuk kemudahan membuat objek baru (opsional)
    public Product(String name, BigDecimal price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // GETTER
    public UUID getId(){ return id; }
    public String getName(){ return name; }
    public BigDecimal getPrice(){ return price; }
    public Integer getStock(){ return stock; }

    // SETTER
    public void setName(String name){ this.name = name; }
    public void setPrice(BigDecimal price){ this.price = price; }
    public void setStock(Integer stock){ this.stock = stock; }

}
