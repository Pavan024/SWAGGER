package com.wavelabs.springbootswaggerDB.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wavelabs.springbootswaggerDB.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findByName(String name);
}
