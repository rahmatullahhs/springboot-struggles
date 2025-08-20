package com.emranhss.merchandise.entity;

import jakarta.persistence.*;

import java.util.List;

@Table(name="totalpurchase")
@Entity
public class TotalPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double price;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Product> products;




    }


