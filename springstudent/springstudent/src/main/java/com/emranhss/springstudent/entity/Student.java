package com.emranhss.springstudent.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public interface Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float salary;
    private String designation;

}
