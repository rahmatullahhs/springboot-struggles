package com.emranhss.merchandise.RestController;

import com.emranhss.merchandise.entity.Employee;
import com.emranhss.merchandise.repository.EmployeeRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Employee")
public class EmployeeController {

    private final EmployeeRepo employeeRepo;

    public EmployeeController(EmployeeRepo employeeRepo){
        this.employeeRepo=employeeRepo;
    }
    //create
    @PostMapping
    public Employee addEmp(@RequestBody Employee employee){
        return  employeeRepo.save(employee);
    }

    //read all
    @GetMapping
    public List<Employee>getAllEmp(){
        return employeeRepo.findAll();
    }

    //read one
    @GetMapping("/{id}")
    public Optional<Employee>getAllEmpById(@PathVariable Long id){
        return  employeeRepo.findById(id);
    }

    //update
    @PutMapping("/{id}")
    public  Employee updateEmp(@PathVariable Long id ,@RequestBody Employee employeeDetails){
        Employee employee= employeeRepo.findById(id).orElseThrow();
        return  employeeRepo.save(employee);
    }

    //delete
    @DeleteMapping("/{id}")
    public  void  deleteEmp(@PathVariable Long id){
        employeeRepo.deleteById(id);

    }

}
