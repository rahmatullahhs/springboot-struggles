package com.emranhss.merchandise.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Or GenerationType.IDENTITY if you're using numeric IDs
    private Long id;
    private String goodsName;
    private String details;
    private String invoice;
    private Date date;
    private int qty;
    private double price;
    private double paid;
    private double due;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="supplier_id")
    private Supplier supplier;





}
