package com.emranhss.merchandise.RestController;

import com.emranhss.merchandise.entity.ReInvoice;
import com.emranhss.merchandise.repository.ReInvoiceRepo;
import com.emranhss.merchandise.service.ReInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reinvoices")
@CrossOrigin("*")
public class ReinvoiceController {

@Autowired
private ReInvoiceService reInvoiceService;
        @Autowired
        private ReInvoiceRepo repo;

    @PostMapping
    public ReInvoice saveReinvoice(@RequestBody ReInvoice reinvoice) {
        return repo.save(reinvoice);
    }


    @GetMapping
        public List<ReInvoice> getAllReinvoices() {
            return repo.findAll();
        }


        @DeleteMapping("/{id}")
        public void deleteReinvoice(@PathVariable Long id) {
            repo.deleteById(id);
        }
    }



