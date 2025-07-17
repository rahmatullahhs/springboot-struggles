package com.emranhss.projectrahmat.restcontrol;

import com.emranhss.projectrahmat.dto.CountryResponseDTO;
import com.emranhss.projectrahmat.entity.Country;
import com.emranhss.projectrahmat.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries/")

public class CuntryRestControl {

    @Autowired
    private CountryService countryService;

    @GetMapping("")
    public ResponseEntity<List<CountryResponseDTO>> getCountries() {
        List<CountryResponseDTO> dtoList = countryService.getAllCountryDTOs();
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("")
    public ResponseEntity<Country> createCountry(@RequestBody Country country) {
        Country saved = countryService.saveCountry(country);
        return ResponseEntity.ok(saved);
    }








}
