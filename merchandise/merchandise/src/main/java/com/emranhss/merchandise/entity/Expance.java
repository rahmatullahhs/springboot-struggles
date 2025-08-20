package com.emranhss.merchandise.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "expance")
public class Expance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private float amount;

    @ManyToOne
    @JoinColumn(name = "employee_id") // Foreign key to the Employee table
    private Employee employee;

    public Expance() {
    }

    public Expance(Long id, String description, float amount, Employee employee) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.employee = employee;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

