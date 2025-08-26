package com.emranhss.merchandise.RestController;

import com.emranhss.merchandise.entity.Cogs;
import com.emranhss.merchandise.service.CogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cogs")
@CrossOrigin("*")
public class CogsController {

    private final CogsService cogsService;

    @Autowired
    public CogsController(CogsService cogsService) {
        this.cogsService = cogsService;
    }

    // Create
    @PostMapping("/add")
    public Cogs saveCogs(@RequestBody Cogs cogs) {
        return cogsService.createCogs(cogs);
    }

    // Read one
    @GetMapping("/{id}")
    public Cogs getCogsById(@PathVariable Long id) {
        return cogsService.getCogsById(id);
    }

    // Read all
    @GetMapping("")
    public List<Cogs> getAllCogs() {
        return cogsService.getAllCogs();
    }

    // Update
    @PutMapping("/{id}")
    public Cogs updateCogs(@PathVariable Long id, @RequestBody Cogs cogs) {
        return cogsService.updateCogs(id, cogs);
    }

    // Delete
    @DeleteMapping("/{id}")
    public void deleteCogs(@PathVariable Long id) {
        cogsService.deleteCogs(id);
    }
}
