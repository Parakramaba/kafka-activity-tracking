package com.parakramaba.producerservice.controller;

import com.parakramaba.producerservice.dto.ProductDetailsDto;
import com.parakramaba.producerservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/producer/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/new")
    public ResponseEntity<?> createProduct(final @RequestBody ProductDetailsDto productDetailsDto) {
        return productService.createProduct(productDetailsDto);
    }
}
