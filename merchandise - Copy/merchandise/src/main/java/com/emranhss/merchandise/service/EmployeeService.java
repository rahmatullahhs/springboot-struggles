package com.emranhss.merchandise.service;

import com.emranhss.merchandise.entity.Employee;
import com.emranhss.merchandise.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Value("${image.upload.dir}")
    private String uploadDir;

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepo.findById(id).orElse(null);
    }

    public Employee createEmployee(Employee employee, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            String fileName = saveImage(file, employee);
            employee.setPhoto( fileName);
        }
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepo.findById(id).map(existing -> {
            existing.setName(updatedEmployee.getName());
            existing.setEmail(updatedEmployee.getEmail());
            existing.setPhone(updatedEmployee.getPhone());
            existing.setAddress(updatedEmployee.getAddress());
            existing.setGender(updatedEmployee.getGender());
            existing.setDesignation(updatedEmployee.getDesignation());
            existing.setSalary(updatedEmployee.getSalary());
            return employeeRepo.save(existing);
        }).orElse(null);
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    public String saveImage(MultipartFile file, Employee employee) {
        Path uploadPath = Paths.get(uploadDir, "employee");

        try {
            Files.createDirectories(uploadPath);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory!", e);
        }

        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        }

        String fileName = employee.getName().replaceAll("\\s+", "_") + "_" + UUID.randomUUID() + extension;

        try {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Could not save image file!", e);
        }

        return fileName;
    }
}
