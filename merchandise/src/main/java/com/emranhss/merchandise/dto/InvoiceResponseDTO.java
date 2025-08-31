package com.emranhss.merchandise.dto;

import com.emranhss.merchandise.entity.Invoice;
import com.emranhss.merchandise.entity.Product;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceResponseDTO {

    private Long id;
    private Date date;
    private String invoiceNumber;
//customer details
    private String name;
    private String email;
    private String phone;
    private String address;

    //product details
    private String productName;
    private int quantity;


    private double subtotal;
    private double discount;
    private double taxRate = 5.0;
    private double taxAmount;
    private double total;

    private double paid;
    private double due;

    public InvoiceResponseDTO() {
    }

    public InvoiceResponseDTO(Long id, Date date, String invoiceNumber, String name, String email, String phone, String address, String productName, int quantity, double subtotal, double discount, double taxRate, double taxAmount, double total, double paid, double due) {
        this.id = id;
        this.date = date;
        this.invoiceNumber = invoiceNumber;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.productName = productName;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.discount = discount;
        this.taxRate = taxRate;
        this.taxAmount = taxAmount;
        this.total = total;
        this.paid = paid;
        this.due = due;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
