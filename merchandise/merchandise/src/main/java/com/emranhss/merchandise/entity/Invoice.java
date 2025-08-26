package com.emranhss.merchandise.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "", cascade = CascadeType.ALL)
    private List<Brand> brands;


}
