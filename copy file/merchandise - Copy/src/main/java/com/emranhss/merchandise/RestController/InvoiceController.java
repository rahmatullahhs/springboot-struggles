package com.emranhss.merchandise.RestController;

import com.emranhss.merchandise.dto.*;
import com.emranhss.merchandise.entity.Invoice;
import com.emranhss.merchandise.entity.Product;
import com.emranhss.merchandise.repository.InvoiceRepo;

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

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private  InvoiceRepo invoiceRepo;


   // ✅ Create Invoice
    @PostMapping
    public ResponseEntity<InvoiceResponseDTO> createInvoice(@RequestBody Invoice invoice) {
        Invoice saved = invoiceService.save(invoice);
        return ResponseEntity.ok(InvoiceMapper.toDTO(saved));
    }

//    {
//        "name": "John Doe",
//            "email": "john@example.com",
//            "phone": "123456789",
//            "address": "Dhaka, Bangladesh",
//            "discount": 100,
//            "paid": 500,
//            "products": [
//        {
//            "id": 1,   // stock product ID (from DB)
//                "quantity": 1
//        },
//        {
//            "id": 5,   // stock product ID (from DB)
//                "quantity": 2
//        }
//  ]
//    }


//    response

//    {
//        "id": 6,
//            "date": "2025-09-03T15:59:15.0759763",
//            "name": "John Doe",
//            "email": "john@example.com",
//            "phone": "123456789",
//            "address": "Dhaka, Bangladesh",
//            "subtotal": 100025.99,
//            "discount": 100.0,
//            "taxRate": 5.0,
//            "taxAmount": 4996.299500000001,
//            "total": 104922.28950000001,
//            "paid": 500.0,
//            "due": 104422.28950000001,
//            "invoiceNumber": "INV-1756893555075",
//            "products": [
//        {
//            "id": 16,
//                "name": "Logitech Mouse",
//                "category": "Accessory",
//                "brand": "Logitech",
//                "model": "M185",
//                "details": "Compact wireless mouse",
//                "quantity": 1,
//                "price": 25.99
//        },
//        {
//            "id": 17,
//                "name": "dddd",
//                "category": "Accessory",
//                "brand": "dddd",
//                "model": "dddd",
//                "details": "ddddd",
//                "quantity": 2,
//                "price": 50000.0
//        }
//    ]
//    }




    // ✅ Get All Invoices
    @GetMapping
    public ResponseEntity<List<InvoiceResponseDTO>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAll();
        List<InvoiceResponseDTO> invoiceDTOs = invoices.stream()
                .map(InvoiceMapper::toDTO)
                .toList();
        return ResponseEntity.ok(invoiceDTOs);
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

    //dashboard sell

    @GetMapping("/sellsummary")
    public SalesSummaryDTO getSalesSummary() {
        Double today = invoiceService.getTodaySales();
        Double last7 = invoiceService.getLast7DaysSales();
        Double last30 = invoiceService.getLast30DaysSales();

        return new SalesSummaryDTO(today, last7, last30);
    }


    // salesdashboard end


}
