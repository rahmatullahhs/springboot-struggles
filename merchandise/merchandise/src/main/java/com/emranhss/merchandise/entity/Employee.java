package com.emranhss.merchandise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private  String name;
    private  String email;
    private  String phone;
    private  String nid;
    private  String address;
    private  String gender;
    private  String designation;
    private  Number salary;

    public Employee() {
    }

    public Employee(Long id, String name, String email, String phone, String nid, String address, String gender, String designation, Number salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.nid = nid;
        this.address = address;
        this.gender = gender;
        this.designation = designation;
        this.salary = salary;
    }

    public Number getSalary() {
        return salary;
    }

    public void setSalary(Number salary) {
        this.salary = salary;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
