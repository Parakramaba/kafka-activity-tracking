package com.parakramaba.producerservice.repository;

import com.parakramaba.producerservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
