package com.Atiq.Springproject.RestController;


import com.Atiq.Springproject.Entity.Sales;
import com.Atiq.Springproject.Service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")


public class SalesRestController {



    @Autowired
    private SalesService salesService;

    @GetMapping("/")
    public List<Sales> getAllSales(){
        return salesService.getAllSales();
    }

    // Create sales for "Dhanmondi" branch
    @PostMapping("/dhanmondi")
    public ResponseEntity<Sales> createSalesForDhanmondi(@RequestBody Sales sales) {
        Sales createdSales = salesService.saveSalesForDhanmondi(sales);
        return ResponseEntity.ok(createdSales);
    }

    // Create sales for "Banani" branch
    @PostMapping("/banani")
    public ResponseEntity<Sales> createSalesForBanani(@RequestBody Sales sales) {
        Sales createdSales = salesService.saveSalesForBanani(sales);
        return ResponseEntity.ok(createdSales);
    }

    @PostMapping("/gulshan")
    public ResponseEntity<Sales> createSalesForGulshan(@RequestBody Sales sales) {
        Sales createdSales = salesService.saveSalesForGulshan(sales);
        return ResponseEntity.ok(createdSales);
    }

    // Delete sales by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSales(@PathVariable int id) {
        salesService.deleteSalesById(id);
        return ResponseEntity.noContent().build();
    }

}
