package com.emranhss.merchandise.service;

import com.emranhss.merchandise.entity.DueList;
import com.emranhss.merchandise.entity.Invoice;
import com.emranhss.merchandise.entity.Product;
import com.emranhss.merchandise.repository.DueListRepo;
import com.emranhss.merchandise.repository.InvoiceRepo;
import com.emranhss.merchandise.repository.ProductRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepo invoiceRepo;
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private DueListRepo dueListRepo;



//    public Invoice save(Invoice invoice) {
//        // Re-fetch each product from the DB (optional step depending on use case)
//        List<Product> managedProducts = invoice.getProducts().stream()
//                .map(p -> productRepo.findById(p.getId())
//                        .orElseThrow(() -> new RuntimeException("Product not found with id: " + p.getId())))
//                .toList();
//
//        // Set the invoice reference in each product
//        managedProducts.forEach(p -> p.setInvoice(invoice));
//
//        // Set the managed products back into invoice
//        invoice.setProducts(managedProducts);
//
//        // Calculate totals before saving
//        invoice.calculateTotals();
//
//        // Save the invoice along with cascade to products
//        return invoiceRepo.save(invoice);
//
//
//
//    }



    @Transactional
    public Invoice save(Invoice invoice) {
        List<Product> soldProducts = new ArrayList<>();

        for (Product invoiceProduct : invoice.getProducts()) {
            // Fetch stock product
            Product stockProduct = productRepo.findById(invoiceProduct.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + invoiceProduct.getId()));

            // Deduct stock
            int newQuantity = stockProduct.getQuantity() - invoiceProduct.getQuantity();
            if (newQuantity < 0) {
                throw new RuntimeException("Not enough stock for product: " + stockProduct.getName());
            }
            stockProduct.setQuantity(newQuantity);


            //productRepo.save(stockProduct);

            // Create snapshot product for invoice
            Product sold = new Product();
            sold.setName(stockProduct.getName());
            sold.setCategory(stockProduct.getCategory());
            sold.setBrand(stockProduct.getBrand());
            sold.setModel(stockProduct.getModel());
            sold.setDetails(stockProduct.getDetails());
            sold.setPrice(stockProduct.getPrice());
            sold.setQuantity(invoiceProduct.getQuantity()); // sold qty
            sold.setInvoice(invoice);

            soldProducts.add(sold);
        }

        invoice.setProducts(soldProducts);

        // Set defaults if not provided
        if (invoice.getDate() == null) {
            invoice.setDate(LocalDateTime.now());
        }
        if (invoice.getInvoiceNumber() == null) {
            invoice.setInvoiceNumber("INV-" + System.currentTimeMillis());
        }

        invoice.calculateTotals();

        Invoice savedInvoice = invoiceRepo.save(invoice);

        // ✅ Save into DueList
        if (savedInvoice.getDue() > 0) {
            DueList dueEntry = new DueList();
            dueEntry.setInvoiceNumber(savedInvoice.getInvoiceNumber());
            dueEntry.setDate(java.sql.Timestamp.valueOf(savedInvoice.getDate()));
            dueEntry.setCustomerName(savedInvoice.getName());
            dueEntry.setTotalAmount(savedInvoice.getTotal());
            dueEntry.setDue(savedInvoice.getDue());
            dueEntry.setPayment(savedInvoice.getPaid());

            dueListRepo.save(dueEntry);
        }

        return savedInvoice;
    }





//    @Transactional
//    public Invoice save(Invoice invoice) {
//        // Re-fetch each product from the DB
//        List<Product> managedProducts = invoice.getProducts().stream()
//                .map(p -> productRepo.findById(p.getId())
//                        .orElseThrow(() -> new RuntimeException("Product not found with id: " + p.getId())))
//                .toList();
//        // Deduct quantities
//        for (int i = 0; i < managedProducts.size(); i++) {
//            Product managedProduct = managedProducts.get(i);
//            Product invoiceProduct = invoice.getProducts().get(i);
//
//            int newQuantity = managedProduct.getQuantity() - invoiceProduct.getQuantity();
//
//            if (newQuantity < 0) {
//                throw new RuntimeException("Not enough stock for product: " + managedProduct.getName());
//            }
//            managedProduct.setQuantity(newQuantity);
//            managedProduct.setInvoice(invoice); // link to invoice
//            productRepo.save(managedProduct);   // update stock
//        }
//        // Update invoice with managed products
//        invoice.setProducts(managedProducts);
//        // Calculate totals
//        invoice.calculateTotals();
//        // Save the invoice
//        return invoiceRepo.save(invoice);
//    }

//    public Invoice save(Invoice invoice) {
//        // Save invoice first without products so it gets a valid ID
//        invoice.setProducts(null); // Detach products temporarily
//        Invoice savedInvoice = invoiceRepo.save(invoice); // ✅ invoice now has ID
//
//        // Re-fetch and update product inventory, then link to invoice
//        List<Product> updatedProducts = invoice.getProducts().stream().map(p -> {
//            Product dbProduct = productRepo.findById(p.getId())
//                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + p.getId()));
//
//            int newQuantity = dbProduct.getQuantity() - p.getQuantity();
//
//            if (newQuantity < 0) {
//                throw new RuntimeException("Not enough stock for product: " + dbProduct.getName());
//            }
//
//            dbProduct.setQuantity(newQuantity);
//            dbProduct.setInvoice(savedInvoice); // ✅ link invoice after it has an ID
//
//            return dbProduct;
//        }).toList();
//
//        // Save updated products
//        productRepo.saveAll(updatedProducts);
//
//        // Now update invoice with correct products
//        savedInvoice.setProducts(updatedProducts);
//        savedInvoice.calculateTotals(); // re-calculate based on updated data
//
//        return invoiceRepo.save(savedInvoice); // ✅ final update with correct totals and linked products
//    }


    public List<Invoice> getAll() {
        return invoiceRepo.findAll();
    }

    public Optional<Invoice> getById(Long id) {
        return invoiceRepo.findById(id);
    }

    public void delete(Long id) {
        invoiceRepo.deleteById(id);
    }

}
