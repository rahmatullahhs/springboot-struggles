package com.emranhss.projectrahmat.service;

import com.emranhss.projectrahmat.entity.PoliceStation;
import com.emranhss.projectrahmat.repository.IPoliceStationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoliceStationService {

    @Autowired
    private IPoliceStationRepo policeStationRepo;

    public void save(PoliceStation ps){
        policeStationRepo.save(ps);
        
    }






}
