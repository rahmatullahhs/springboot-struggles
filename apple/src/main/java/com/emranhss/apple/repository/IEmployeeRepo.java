package com.emranhss.apple.repository;

import com.emranhss.apple.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepo  extends JpaRepository<Employee,Integer> {
}
