package com.emranhss.merchandise.service;

import com.emranhss.merchandise.entity.Employee;
import com.emranhss.merchandise.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {


    @Autowired
    private EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepo.findById(id).orElse(null);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepo.findById(id).map(emp -> {
            return employeeRepo.save(emp);
        }).orElse(null);
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

}
