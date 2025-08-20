package com.emranhss.merchandise.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "totalBuy")
public class TotalBuy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    public TotalBuy() {}

    public TotalBuy(double amount) {
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
