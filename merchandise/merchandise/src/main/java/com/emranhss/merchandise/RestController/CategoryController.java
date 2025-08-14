package com.emranhss.merchandise.RestController;

import com.emranhss.merchandise.entity.Category;
import com.emranhss.merchandise.entity.Employee;
import com.emranhss.merchandise.repository.CategoryRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("*")
public class CategoryController {


    private final CategoryRepo categoryRepo;

    public CategoryController(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    //create
    @PostMapping("add")
    public Category addCatagory(@RequestBody Category category) {
        return categoryRepo.save(category);
    }

    //read all
    @GetMapping("")
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    //read one
    @GetMapping("/{id}")
    public Optional<Category> getAllCategoryById(@PathVariable Long id) {
        return categoryRepo.findById(id);
    }


    // Update
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        return categoryRepo.findById(id)
                .map(category -> {
                    category.setName(categoryDetails.getName());
                    Category updatedCategory = categoryRepo.save(category);
                    return categoryRepo.save(updatedCategory);
                })
                .orElseThrow();
    }
    //delete
    @DeleteMapping("{id}")
    public  void  deleteCategory(@PathVariable Long id){
        categoryRepo.deleteById(id);

}}