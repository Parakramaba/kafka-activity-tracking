package com.parakramaba.producerservice.service;

import com.parakramaba.producerservice.dto.ProductDetailsDto;
import com.parakramaba.producerservice.entity.Product;
import com.parakramaba.producerservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("ProductService")
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * This creates a new product.
     * @param productDetailsDto Details of the product, not null
     * @return Response entity with success message
     */
    public ResponseEntity<?> createProduct(final ProductDetailsDto productDetailsDto) {

        Product product = new Product();
        String productId = UUID.randomUUID().toString();

        product.setId(productId);
        product.setName(productDetailsDto.getName());
        product.setBrand(productDetailsDto.getBrand());
        product.setPrice(productDetailsDto.getPrice());

        productRepository.save(product);

        return new ResponseEntity<>("Product has been created successfully", HttpStatus.CREATED);
    }
}
