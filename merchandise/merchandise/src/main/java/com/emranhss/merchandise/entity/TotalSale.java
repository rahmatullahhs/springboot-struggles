package com.emranhss.merchandise.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "totalSale")
public class TotalSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    public TotalSale() {}

    public TotalSale(double amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
