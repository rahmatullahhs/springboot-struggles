package com.emranhss.apple.restcontroller;

import com.emranhss.apple.entity.Employee;
import com.emranhss.apple.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/employee/")
public class EmployeeRestController {


    @Autowired
    private EmployeeService employeeService;

    @PostMapping ("")
    public void save(@RequestBody Employee e) {
        employeeService.saveOrUpdate(e);
    }


    @GetMapping ("")
    public List<Employee> getAll() {

        return employeeService.findAll();
    }

    @GetMapping("{id}")
    public Employee getById(@PathVariable Integer id) {

        return employeeService.findById(id).get();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {

        employeeService.deleteById(id);
    }

    @PutMapping ("{id}")
    public void Update(@RequestBody Employee e) {

        employeeService.saveOrUpdate(e);

    }

}
