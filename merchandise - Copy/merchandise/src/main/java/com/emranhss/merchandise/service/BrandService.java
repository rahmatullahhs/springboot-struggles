package com.emranhss.merchandise.service;

import com.emranhss.merchandise.entity.Brand;
import com.emranhss.merchandise.repository.BrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    private BrandRepo brandRepo;

    // ✅ Return all brands
    public List<Brand> getAllBrands() {
        return brandRepo.findAll();
    }

    // ✅ Get a brand by its ID
    public Optional<Brand> getBrandById(Long id) {
        return brandRepo.findById(id);
    }

    // ✅ Create a new brand
    public Brand createBrand(Brand brand) {
        return brandRepo.save(brand);
    }

    // ✅ Update an existing brand
    public Optional<Brand> updateBrand(Long id, Brand updatedBrand) {
        return brandRepo.findById(id).map(existingBrand -> {
            existingBrand.setName(updatedBrand.getName());
            existingBrand.setCategoryId(updatedBrand.getCategoryId());
            return brandRepo.save(existingBrand);
        });
    }

    // ✅ Delete a brand
    public void deleteBrand(Long id) {
        brandRepo.deleteById(id);
    }
}
