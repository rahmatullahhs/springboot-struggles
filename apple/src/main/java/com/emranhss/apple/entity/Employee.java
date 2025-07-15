package com.emranhss.apple.entity;


import jakarta.persistence.*;

@Entity
@Table (name="employees")
public class Employee {
    @Id
@GeneratedValue (strategy= GenerationType.IDENTITY)
    private int id;


    @Column(length = 50, nullable = false)
    private String name;

    public Employee() {
    }

    public Employee(int id, String name) {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}



