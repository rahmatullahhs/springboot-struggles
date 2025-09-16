package com.emranhss.merchandise.service;
import com.emranhss.merchandise.dto.RoleManagerDTO;
import com.emranhss.merchandise.entity.RoleManager;
import com.emranhss.merchandise.repository.RoleManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleManagerService {

    @Autowired
    private RoleManagerRepo roleManagerRepo;

    public List<RoleManager> getAllManagers() {
        return roleManagerRepo.findAll();

    }


    public List<RoleManagerDTO> getAllRoleManagerDTO() {
        return roleManagerRepo.findAll().stream().map(manager -> {
            RoleManagerDTO dto = new RoleManagerDTO();


            dto.setId(manager.getId());
            dto.setEmail(manager.getEmail());
            dto.setName(manager.getName());
            dto.setAddress(manager.getAddress());
            dto.setPhone(manager.getPhone());
            dto.setGender(manager.getGender());
            dto.setPhoto(manager.getPhoto());
            dto.setDateOfBirth(manager.getDateOfBirth());


            return dto;
        }).toList();
    }



    public RoleManager save(RoleManager roleManager) {
        return roleManagerRepo.save(roleManager);
    }

    public void delete(Long id) {
        roleManagerRepo.deleteById(id);
    }

    public RoleManager getProfileByUserId(int userId) {
        return roleManagerRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Role Manager not found"));
    }
}


