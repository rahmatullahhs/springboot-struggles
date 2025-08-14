package com.emranhss.merchandise.entity;
import jakarta.persistence.*;

import java.util.List;

@Table(name="category")
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String name;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<Brand>brand;

    public Category() {
    }

    public Category(long id, String name, List<Brand> brand) {
        this.id = id;
        this.name = name;
        this.brand = brand;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Brand> getBrand() {
        return brand;
    }

    public void setBrand(List<Brand> brand) {
        this.brand = brand;
    }
}
