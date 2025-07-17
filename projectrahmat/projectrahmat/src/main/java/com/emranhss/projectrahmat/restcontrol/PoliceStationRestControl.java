package com.emranhss.projectrahmat.restcontrol;

import com.emranhss.projectrahmat.entity.PoliceStation;
import com.emranhss.projectrahmat.service.PoliceStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policestation")
public class PoliceStationRestControl {
    @Autowired
    private PoliceStationService policeStationService;


    @PostMapping("")
    public void save(@RequestBody PoliceStation ps) {
        policeStationService.saveOrUpdate(ps);
    }

    @GetMapping("")
    public List<PoliceStation> getAll() {

        return policeStationService.findAll();
    }



    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Integer id) {

        policeStationService.deleteById(id);
    }

    @PutMapping("{id}")
    public void Update(@RequestBody PoliceStation ps) {

        policeStationService.saveOrUpdate(ps);

    }



}
