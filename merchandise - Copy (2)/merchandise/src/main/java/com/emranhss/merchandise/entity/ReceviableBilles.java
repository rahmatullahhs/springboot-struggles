package com.emranhss.merchandise.entity;

import jakarta.persistence.*;

@Table(name="receviableBilles")
@Entity
public class ReceviableBilles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
