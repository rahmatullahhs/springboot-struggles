package com.emranhss.merchandise.service;
import com.emranhss.merchandise.entity.Brand;
import com.emranhss.merchandise.entity.Category;
import com.emranhss.merchandise.entity.DueList;
import com.emranhss.merchandise.repository.DueListRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DueListService {

    @Autowired
    private DueListRepo dueListRepo;


    // ✅ Return all brands
    public List<DueList> getAllBrand() {
        return brandRepo.findAll();
    }

    // ✅ Get a brand by ID
    public Optional<Brand> getBrandById(Long id) {
        return brandRepo.findById(id);
    }

    // ✅ Create a new brand
    @Transactional
    public Brand create(Brand brand) {
        if (brand.getCategory() != null) {
            Long categoryId = brand.getCategory().getId();
            Category category = categoryRepo.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
            brand.setCategory(category);
        }
        return brandRepo.save(brand);
    }



    // ✅ Delete a brand
    public void deleteBrand(Long id) {
        brandRepo.deleteById(id);
    }
}







