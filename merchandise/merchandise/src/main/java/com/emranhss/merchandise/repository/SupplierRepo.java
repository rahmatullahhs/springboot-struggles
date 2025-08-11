package com.emranhss.merchandise.repository;

import com.emranhss.merchandise.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepo extends JpaRepository<Supplier,Long> {
}
