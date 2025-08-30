package com.emranhss.merchandise.RestController;

import com.emranhss.merchandise.entity.Invoice;
import com.emranhss.merchandise.entity.Product;
import com.emranhss.merchandise.repository.InvoiceRepo;
import com.emranhss.merchandise.repository.ProductRepo;
import com.emranhss.merchandise.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceRepo invoiceRepo;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, InvoiceRepo invoiceRepo) {
        this.invoiceService = invoiceService;
        this.invoiceRepo = invoiceRepo;
    }

    // ✅ Create Invoice
    @PostMapping("/add")
    public Invoice addInvoice(@RequestBody Invoice invoice) {
        return invoiceService.save(invoice);
    }

    // ✅ Get All Invoices
    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAll();
    }

    // ✅ Get Invoice by ID
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        return invoiceService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Update Invoice
    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoiceDetails) {
        Optional<Invoice> optionalInvoice = invoiceRepo.findById(id);

        if (optionalInvoice.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Invoice invoice = optionalInvoice.get();

        // Update fields
        invoice.setName(invoiceDetails.getName());
        invoice.setEmail(invoiceDetails.getEmail());
        invoice.setPhone(invoiceDetails.getPhone());
        invoice.setAddress(invoiceDetails.getAddress());
        invoice.setDiscount(invoiceDetails.getDiscount());
        invoice.setTaxRate(invoiceDetails.getTaxRate());
        invoice.setPaid(invoiceDetails.getPaid());
        invoice.setInvoiceNumber(invoiceDetails.getInvoiceNumber());

        // Clear and replace products
        invoice.getProducts().clear();
        if (invoiceDetails.getProducts() != null) {
            for (Product product : invoiceDetails.getProducts()) {
                product.setInvoice(invoice);
                invoice.getProducts().add(product);
            }
        }

        // Recalculate and save
        invoice.calculateTotals();
        Invoice updatedInvoice = invoiceRepo.save(invoice);
        return ResponseEntity.ok(updatedInvoice);
    }

    // ✅ Delete Invoice
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        if (!invoiceRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        invoiceRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
