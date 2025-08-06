package com.emranhss.merchandise.service;

import com.emranhss.merchandise.entity.Category;
import com.emranhss.merchandise.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    // সব ক্যাটাগরি রিটার্ন করে
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    // নির্দিষ্ট ID দিয়ে ক্যাটাগরি খোঁজে
    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id).orElse(null);
    }

    // নতুন ক্যাটাগরি তৈরি করে
    public Category createCategory(Category category) {
        return categoryRepo.save(category); // এখানে আগে ভুলভাবে capital `Category` ছিল
    }

    // ক্যাটাগরি আপডেট করে
    public Category updateCategory(Long id, Category updatedCategory) {
        return categoryRepo.findById(id).map(existingCategory -> {
            existingCategory.setName(updatedCategory.getName()); // ধরলাম `name` আছে
            // আরও ফিল্ড থাকলে সেগুলিও সেট করতে হবে
            return categoryRepo.save(existingCategory); // এখানে ভুল ছিল - ভুলভাবে `CategoryRepo.save(...)` লেখা ছিল
        }).orElse(null);
    }

    // ক্যাটাগরি ডিলিট করে
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
}
