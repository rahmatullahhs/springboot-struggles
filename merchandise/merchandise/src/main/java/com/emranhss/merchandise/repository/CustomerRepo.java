package com.emranhss.merchandise.repository;

import com.emranhss.merchandise.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
}
