package com.emranhss.merchandise.RestController;

import com.emranhss.merchandise.entity.ReInvoice;
import com.emranhss.merchandise.repository.ReInvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reinvoices")
@CrossOrigin("*")
public class ReinvoiceController {


        @Autowired
        private ReInvoiceRepo repo;

        @GetMapping
        public List<ReInvoice> getAllReinvoices() {
            return repo.findAll();
        }

        @PostMapping
        public ReInvoice saveReinvoice(@RequestBody ReInvoice reinvoice) {
            return repo.save(reinvoice);
        }

        @DeleteMapping("/{id}")
        public void deleteReinvoice(@PathVariable Long id) {
            repo.deleteById(id);
        }
    }



