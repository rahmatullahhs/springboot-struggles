package com.emranhss.merchandise.RestController;

import com.emranhss.merchandise.entity.Employee;
import com.emranhss.merchandise.repository.EmployeeRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin("*")
public class EmployeeController {

    private final EmployeeRepo employeeRepo;

    public EmployeeController(EmployeeRepo employeeRepo){
        this.employeeRepo=employeeRepo;
    }
    //create
    @PostMapping("add")
    public Employee addEmp(@RequestBody Employee employee){

        return  employeeRepo.save(employee);
    }

    //read all
    @GetMapping("")
    public List<Employee>getAllEmp(){
        return employeeRepo.findAll();
    }

    //read one
    @GetMapping("/{id}")
    public Optional<Employee>getAllEmpById(@PathVariable Long id){
        return  employeeRepo.findById(id);
    }


    // Update
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return employeeRepo.findById(id)
                .map(employee -> {
                    employee.setName(employeeDetails.getName());
                    employee.setEmail(employeeDetails.getEmail());
                    employee.setPhone(employeeDetails.getPhone());
                    employee.setNid(employeeDetails.getNid());
                    employee.setAddress(employeeDetails.getAddress());
                    employee.setGender(employeeDetails.getGender());
                    employee.setDesignation(employeeDetails.getDesignation());
                    employee.setSalary(employeeDetails.getSalary());

                    Employee updatedEmployee = employeeRepo.save(employee);
                    return employeeRepo.save(updatedEmployee);
                })
                .orElseThrow();
    }
    //delete
    @DeleteMapping("{id}")
    public  void  deleteEmp(@PathVariable Long id){
        employeeRepo.deleteById(id);

    }

}
