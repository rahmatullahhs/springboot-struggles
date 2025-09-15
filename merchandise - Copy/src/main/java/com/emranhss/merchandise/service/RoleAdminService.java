package com.emranhss.merchandise.service;

import com.emranhss.merchandise.dto.RoleAdminResponseDTO;
import com.emranhss.merchandise.entity.RoleAdmin;
import com.emranhss.merchandise.repository.RoleAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleAdminService {

    @Autowired
    private RoleAdminRepo roleAdminRepo;

    public List<RoleAdmin> getAllAdmin() {
        return roleAdminRepo.findAll();
    }


    public List<RoleAdminResponseDTO> getAllRoleAdminResponseDTOS() {
        return roleAdminRepo.findAll().stream().map(admin -> {
            RoleAdminResponseDTO dto = new RoleAdminResponseDTO();


            dto.setId(admin.getId());
            dto.setEmail(admin.getEmail());
            dto.setName(admin.getName());
            dto.setAddress(admin.getAddress());
            dto.setPhone(admin.getPhone());
            dto.setGender(admin.getGender());
            dto.setPhoto(admin.getPhoto());
            dto.setDateOfBirth(admin.getDateOfBirth());


            return dto;
        }).toList();
    }



    public RoleAdmin save(RoleAdmin roleAdmin) {
        return roleAdminRepo.save(roleAdmin);
    }

    public void delete(Long id) {
        roleAdminRepo.deleteById(id);
    }

    public RoleAdmin getProfileByUserId(int userId) {
        return roleAdminRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Role Admin not found"));
    }
}
