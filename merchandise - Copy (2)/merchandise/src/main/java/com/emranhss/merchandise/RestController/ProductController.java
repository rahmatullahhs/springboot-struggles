package com.emranhss.merchandise.RestController;

import com.emranhss.merchandise.entity.Product;
import com.emranhss.merchandise.entity.Supplier;
import com.emranhss.merchandise.repository.ProductRepo;
import com.emranhss.merchandise.repository.SupplierRepo;
import com.emranhss.merchandise.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

  @Autowired
  private final ProductRepo productRepo;

    public ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    // Create
    @PostMapping("add")
    public Product addProduct(@RequestBody Product product) {
        return productRepo.save(product);
    }

    // Read all
    @GetMapping("")
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    // Read one by ID
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productRepo.findById(id);
    }

    // Update
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productRepo.findById(id)
                .map(product -> {
                    product.setName(productDetails.getName());
                    product.setCategory(productDetails.getCategory());
                    product.setBrand(productDetails.getBrand());
                    product.setModel(productDetails.getModel());
                    product.setDetails(productDetails.getDetails());
                    product.setStock(productDetails.getStock());
                    product.setPrice(productDetails.getPrice());

                    return productRepo.save(product);
                })
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));
    }

    // Delete
    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepo.deleteById(id);
    }
}

