package com.emranhss.projectrahmat.service;

import com.emranhss.projectrahmat.dto.CountryResponseDTO;
import com.emranhss.projectrahmat.entity.Country;
import com.emranhss.projectrahmat.repository.ICountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private ICountry countryRepository;

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public List<CountryResponseDTO> getAllCountryDTOs() {
        return getAllCountries().stream().map(c -> {
            CountryResponseDTO dto = new CountryResponseDTO();
            dto.setId(c.getId());
            dto.setName(c.getName());

            List<Integer> divisionIds = c.getDivisions().stream()
                    .map(d -> d.getId())
                    .toList();

            dto.setDivisions(divisionIds);

            return dto;
        }).toList();
    }

    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

}
