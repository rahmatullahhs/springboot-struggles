package com.emranhss.merchandise.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Or GenerationType.IDENTITY if you're using numeric IDs
    private Long id;
    @Column(nullable = false)
    private Long brandId;
    @Column(nullable = false)
    private Long categoryId;
    @Column(nullable = false)
    private String goodsName;
    @Column(columnDefinition = "TEXT")
    private String details;
    @Column(nullable = false)
    private String invoice;
    @Column(nullable = false)
    private String supplierId;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private int qty;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)

    private double paid;
    @Column(nullable = false)
    private double due;

    public Goods() {
    }

    public Goods(Long id, Long brandId, Long categoryId,
                 String goodsName, String details, String invoice,
                 String supplierId, Date date, int qty, double price,
                double paid, double due) {
        this.id = id;
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.goodsName = goodsName;
        this.details = details;
        this.invoice = invoice;
        this.supplierId = supplierId;
        this.date = date;
        this.qty = qty;
        this.price = price;

        this.paid = paid;
        this.due = due;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }





    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public double getDue() {
        return due;
    }

    public void setDue(double due) {
        this.due = due;
    }



}
