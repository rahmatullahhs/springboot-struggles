package com.emranhss.merchandise.service;

import com.emranhss.merchandise.entity.Brand;
import com.emranhss.merchandise.entity.Category;
import com.emranhss.merchandise.repository.BrandRepo;
import com.emranhss.merchandise.repository.CategoryRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepo brandRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    // ✅ Return all brands
    public List<Brand> getAllBrands() {
        return brandRepo.findAll();
    }

    // ✅ Get a brand by its ID
    public Optional<Brand> getBrandById(Long id) {
        return brandRepo.findById(id);
    }

    // ✅ Create a new brand
    @Transactional
    public Brand create(Brand brand) {
        if (brand.getCategory() != null) {
            long categoryId = brand.getCategory().getId();
            Category category = categoryRepo.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found with id " + categoryId));
            brand.setCategory(category);
        }
        return brandRepo.save(brand);
    }

    // ✅ Update an existing brand
    public Brand update(long id, Brand updateBrand) {
        Brand existing = brandRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found with id " + id));

        existing.setName(updateBrand.getName()); // Fixed method call

        if (updateBrand.getCategory() != null) {
            Category category = categoryRepo.findById(updateBrand.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found with id " + updateBrand.getCategory().getId()));
            existing.setCategory(category);
        }

        return brandRepo.save(existing);
    }

    // ✅ Delete a brand
    public void deleteBrand(Long id) {
        brandRepo.deleteById(id);
    }
}
