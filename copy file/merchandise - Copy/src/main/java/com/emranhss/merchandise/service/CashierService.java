package com.emranhss.merchandise.service;
import com.emranhss.merchandise.dto.CashierDTO;
import com.emranhss.merchandise.entity.Admin;
import com.emranhss.merchandise.entity.Cashier;
import com.emranhss.merchandise.repository.CashierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashierService {

    @Autowired
    private CashierRepo cashierRepo;

    public List<Cashier> getAllCashier() {
        return cashierRepo.findAll();
    }


    public List<CashierDTO> getAllCashierDTO() {
        return cashierRepo.findAll().stream().map(cashier -> {
            CashierDTO dto = new CashierDTO();


            dto.setId(cashier.getId());
            dto.setEmail(cashier.getEmail());
            dto.setName(cashier.getName());
            dto.setAddress(cashier.getAddress());
            dto.setPhone(cashier.getPhone());
            dto.setGender(cashier.getGender());
            dto.setPhoto(cashier.getPhoto());
            dto.setDateOfBirth(cashier.getDateOfBirth());


            return dto;
        }).toList();
    }



    public Cashier save(Cashier cashier) {
        return cashierRepo.save(cashier);
    }

    public void delete(Long id) {
        cashierRepo.deleteById(id);
    }

    public Cashier getProfileByUserId(int userId) {
        return cashierRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
    }
}

