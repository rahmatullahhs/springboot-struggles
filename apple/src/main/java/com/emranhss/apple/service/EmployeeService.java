package com.emranhss.apple.service;

import com.emranhss.apple.entity.Employee;
import com.emranhss.apple.repository.IEmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {


    @Autowired
    private IEmployeeRepo EmployeeRepo;


    public void saveOrUpdate(Employee e) {
        EmployeeRepo.save(e);
    }


    public List<Employee> findAll() {

        return EmployeeRepo.findAll();
    }

    public Optional<Employee> findById(Integer id) {
        return EmployeeRepo.findById(id);
    }

    public void deleteById(Integer id) {
        EmployeeRepo.deleteById(id);
    }




}
