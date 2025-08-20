package com.emranhss.merchandise.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "payableBills")
public class PayableBills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many bills can be linked to one invoice
    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    // Getters and Setters
}

