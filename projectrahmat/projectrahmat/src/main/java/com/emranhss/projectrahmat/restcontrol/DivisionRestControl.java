package com.emranhss.projectrahmat.restcontrol;

import com.emranhss.projectrahmat.dto.DivisionResponseDTO;
import com.emranhss.projectrahmat.entity.Division;
import com.emranhss.projectrahmat.service.DivisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/division")
public class DivisionRestControl {


    @Autowired
    private DivisonService divisionService;

    @GetMapping("")
    public ResponseEntity<List<DivisionResponseDTO>> getDivisions() {
        List<DivisionResponseDTO> dtoList = divisionService.getAllDivisionDTOs();
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("")
    public ResponseEntity<Division> createDivision(@RequestBody Division division) {
        Division saved = divisionService.saveDivision(division);
        return ResponseEntity.ok(saved);
    }



}
