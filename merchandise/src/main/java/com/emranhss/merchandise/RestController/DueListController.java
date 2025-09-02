package com.emranhss.merchandise.RestController;


import com.emranhss.merchandise.entity.DueList;

import com.emranhss.merchandise.service.DueListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/duelist")
@CrossOrigin("*")
public class DueListController {

        @Autowired
        private DueListService dueListService;

        // Create
        @PostMapping("/add")
        public DueList addDueList(@RequestBody DueList dueList) {
            return dueListService.create(dueList);
        }

        // Read all
        @GetMapping("")
        public List<DueList> getAllDueList() {
            return dueListService.getAllBrand();
        }

        // Read one
        @GetMapping("/{id}")
        public Optional<DueList> getDueListById(@PathVariable Long id) {
            return dueListService.getDueListById(id);
        }

        // Update
        @PutMapping("/{id}")
        public DueList updateDueList(@PathVariable Long id, @RequestBody DueList brand) {
            return dueListService.update(id, brand);
        }

        // Delete
        @DeleteMapping("/{id}")
        public void deleteDueList(@PathVariable Long id) {
            dueListService.deleteBrand(id);
        }

    }
