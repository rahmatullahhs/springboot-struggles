package com.emranhss.merchandise.service;
import com.emranhss.merchandise.dto.RoleCashierDTO;
import com.emranhss.merchandise.entity.RoleAdmin;
import com.emranhss.merchandise.entity.RoleCashier;
import com.emranhss.merchandise.repository.RoleCashierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleCashierService {

    @Autowired
    private RoleCashierRepo roleCashierRepo;

    public List<RoleCashier> getAllCashier() {
        return roleCashierRepo.findAll();
    }


    public List<RoleCashierDTO> getAllRoleCashierDTO() {
        return roleCashierRepo.findAll().stream().map(cashier -> {
            RoleCashierDTO dto = new RoleCashierDTO();


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



    public RoleCashier save(RoleCashier roleCashier) {
        return roleCashierRepo.save(roleCashier);
    }

    public void delete(Long id) {
        roleCashierRepo.deleteById(id);
    }

    public RoleCashier getProfileByUserId(int userId) {
        return roleCashierRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Role Admin not found"));
    }
}


