package com.emranhss.merchandise.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "damage_or_return_products")
public class DamageOrReturnProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double price;

    @OneToMany(mappedBy = "damageOrReturnProducts", cascade = CascadeType.ALL)
    private List<Product> products;

    // Getters and setters
}
