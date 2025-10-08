package com.Atiq.Springproject.RestController;

import com.Atiq.Springproject.Entity.Customer;
import com.Atiq.Springproject.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")



public class CustomerRestController {

    @Autowired
    private CustomerService customerService;



    @GetMapping("/")
    public List<Customer> getAllCustomer() {

        return customerService.getAllCustomer();
    }

    @PostMapping("/save")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer c) {
        customerService.saveCustomer(c);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCustomer(@PathVariable int id) {

        customerService.deleteCustomerById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity <Customer>updateCustomer(@RequestBody Customer c,@PathVariable int id) {
        Customer customer= customerService.updateCustomer(c,id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  Customer getCustomerById(@PathVariable("id") int id) {

        return  customerService.findByid(id);

    }

}
