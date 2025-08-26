package com.emranhss.merchandise.entity;

import jakarta.persistence.*;
import java.time.LocalDate;


@Table(name = "cogs")
@Entity
public class Cogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String purchaseInvoice;

    @Column(nullable = false)
    private Double productPrice;

    @Column(nullable = false)
    private Double transportFee;

    @Column(nullable = false)
    private Double labourCost;

    @Column(nullable = false)
    private Double packingCost;

    @Column(nullable = false)
    private Double tax;

    @Column(nullable = false)
    private Double totalCogs;

    @Column(nullable = false)
    private LocalDate date;


    public Cogs() {
    }


    public Cogs(Long id, String purchaseInvoice, Double productPrice, Double transportFee, Double labourCost, Double packingCost, Double tax, Double totalCogs, LocalDate date) {
        this.id = id;
        this.purchaseInvoice = purchaseInvoice;
        this.productPrice = productPrice;
        this.transportFee = transportFee;
        this.labourCost = labourCost;
        this.packingCost = packingCost;
        this.tax = tax;
        this.totalCogs = totalCogs;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPurchaseInvoice() {
        return purchaseInvoice;
    }

    public void setPurchaseInvoice(String purchaseInvoice) {
        this.purchaseInvoice = purchaseInvoice;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getTransportFee() {
        return transportFee;
    }

    public void setTransportFee(Double transportFee) {
        this.transportFee = transportFee;
    }

    public Double getLabourCost() {
        return labourCost;
    }

    public void setLabourCost(Double labourCost) {
        this.labourCost = labourCost;
    }

    public Double getPackingCost() {
        return packingCost;
    }

    public void setPackingCost(Double packingCost) {
        this.packingCost = packingCost;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTotalCogs() {
        return totalCogs;
    }

    public void setTotalCogs(Double totalCogs) {
        this.totalCogs = totalCogs;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
