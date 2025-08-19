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

    // Check if product exists by ID
    public boolean existsById(Long id) {
        return productRepo.existsById(id);
    }


    public Product increaseStock(Long productId, int amount) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setStock(product.getStock() + amount);
        return productRepo.save(product);
    }

    public Product decreaseStock(Long productId, int amount) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (product.getStock() < amount) {
            throw new RuntimeException("Not enough stock to decrease");
        }
        product.setStock(product.getStock() - amount);
        return productRepo.save(product);
    }


}
