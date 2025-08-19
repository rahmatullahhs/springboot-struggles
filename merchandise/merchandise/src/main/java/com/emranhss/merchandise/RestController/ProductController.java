package com.emranhss.merchandise.RestController;

import com.emranhss.merchandise.entity.Product;
import com.emranhss.merchandise.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    // Constructor injection (recommended)
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    // Optional: get product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }


    @PatchMapping("/{id}/increase-stock")
    public Product increaseStock(@PathVariable Long id, @RequestParam int amount) {
        return productService.increaseStock(id, amount);
    }

    @PatchMapping("/{id}/decrease-stock")
    public Product decreaseStock(@PathVariable Long id, @RequestParam int amount) {
        return productService.decreaseStock(id, amount);
    }


}
