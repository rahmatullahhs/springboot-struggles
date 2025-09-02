package com.emranhss.merchandise.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

public class DueList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String invoiceNumber;
    private Date date;
    private String customerName;
    private double totalAmount;
    private double due;
    private double payment;



}
