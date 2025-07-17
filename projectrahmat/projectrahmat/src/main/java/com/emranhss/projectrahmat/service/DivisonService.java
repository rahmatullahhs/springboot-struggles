package com.emranhss.projectrahmat.service;

import com.emranhss.projectrahmat.dto.DivisionResponseDTO;
import com.emranhss.projectrahmat.entity.Division;
import com.emranhss.projectrahmat.repository.IDivisionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisonService {
    @Autowired
    private IDivisionRepo divisionRepository;

    public List<Division> getAllDivisions() {
        return divisionRepository.findAll();
    }

    public List<DivisionResponseDTO> getAllDivisionDTOs() {
        return getAllDivisions().stream().map(div -> {
            DivisionResponseDTO dto = new DivisionResponseDTO();
            dto.setId(div.getId());
            dto.setName(div.getName());

            List<Integer> districtIds = div.getDistricts().stream()
                    .map(d -> d.getId())
                    .toList();
            dto.setDistricts(districtIds);

            return dto;
        }).toList();
    }

    public Division saveDivision(Division division) {
        return divisionRepository.save(division);
    }




}
