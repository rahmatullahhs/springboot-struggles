package com.emranhss.merchandise.repository;


import com.emranhss.merchandise.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepo extends JpaRepository<Product,Long> {


}
