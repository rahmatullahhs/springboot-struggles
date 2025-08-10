package com.emranhss.merchandise.repository;

import com.emranhss.merchandise.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepo extends JpaRepository<Brand , Long> {
}
