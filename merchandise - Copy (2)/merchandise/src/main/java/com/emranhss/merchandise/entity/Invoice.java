package com.emranhss.merchandise.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many-to-Many relationship with Product
    @ManyToMany
    @JoinTable(
            name = "invoice_products",
            joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    // One-to-Many relationship with PayableBills
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<PayableBills> payableBills;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<PayableBills> getPayableBills() {
        return payableBills;
    }

    public void setPayableBills(List<PayableBills> payableBills) {
        this.payableBills = payableBills;
    }
}
