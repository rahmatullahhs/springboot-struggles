package com.emranhss.merchandise.service;

import com.emranhss.merchandise.entity.Product;
import com.emranhss.merchandise.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductService {

    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    // Create or update a product
    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    // Get product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }

    // Delete product by ID
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }


}
