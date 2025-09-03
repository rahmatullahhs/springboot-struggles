package com.emranhss.merchandise.service;
import com.emranhss.merchandise.entity.Brand;

import com.emranhss.merchandise.entity.DueList;
import com.emranhss.merchandise.repository.DueListRepo;

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
        return dueListRepo.findAll();
    }

    // ✅ Get a brand by ID
    public Optional<DueList> getBrandById(Long id) {
        return dueListRepo.findById(id);
    }



}







