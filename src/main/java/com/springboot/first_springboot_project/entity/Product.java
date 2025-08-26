package com.springboot.first_springboot_project.entity;

public class Product {
    
    private Long id;
    private String name;
    private double price;
    private int stock;

    //CONSTRUCTOR
    public Product(Long id, String name, double price, int stock){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    //GETTER
    public Long getId(){ return id; }
    public String getName(){ return name; }
    public double getPrice(){ return price; }
    public int getStock(){ return stock; }

    //SETTER
    public void setId(Long id){ this.id = id; }
    public void setName(String name){ this.name = name; }
    public void setPrice(double price){ this.price = price; }
    public void setStock(int stock){ this.stock = stock; }

}
