package com.emranhss.merchandise.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "balance_sheet")
public class BalanceSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // List of sales records
    @OneToMany(mappedBy = "balanceSheet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CheckOut> sales;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CheckOut> getSales() {
        return sales;
    }

    public void setSales(List<CheckOut> sales) {
        this.sales = sales;
        if (sales != null) {
            for (CheckOut sale : sales) {
                sale.setBalanceSheet(this);
            }
        }
    }
}
