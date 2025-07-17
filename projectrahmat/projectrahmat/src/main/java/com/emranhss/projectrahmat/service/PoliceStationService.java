package com.emranhss.projectrahmat.service;

import com.emranhss.projectrahmat.entity.District;
import com.emranhss.projectrahmat.entity.PoliceStation;
import com.emranhss.projectrahmat.repository.IDistrictRepo;
import com.emranhss.projectrahmat.repository.IPoliceStationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliceStationService {

    @Autowired
    private IPoliceStationRepo policeStationRepo;


    @Autowired
    private IDistrictRepo districtRepository;


    public void saveOrUpdate(PoliceStation ps) {
        policeStationRepo.save(ps);
    }

    public List<PoliceStation> findAll() {

        return policeStationRepo.findAll();
    }

    public Optional<PoliceStation> findById(Integer id) {
        return policeStationRepo.findById(id);
    }

    public void deleteById(Integer id) {
        policeStationRepo.deleteById(id);
    }

    public List<PoliceStation> findByDistrict(Integer districtId) {
        return policeStationRepo.findByDistrictId(districtId);
    }

    public PoliceStation assignToDistrict(Integer policeStationId, Integer districtId) {
        PoliceStation ps = policeStationRepo.findById(policeStationId)
                .orElseThrow(() -> new RuntimeException("PoliceStation not found"));

        District district = districtRepository.findById(districtId)
                .orElseThrow(() -> new RuntimeException("District not found"));

        ps.setDistrict(district);
        return policeStationRepo.save(ps);
    }




}
