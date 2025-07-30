package com.emranhss.springstudent.service;

import com.emranhss.springstudent.entity.Student;
import com.emranhss.springstudent.repository.IStudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private IStudentRepo repo;

    public List<Student> getAll() {
        return repo.findAll();
    }

    public Student getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void save(Student employee) {
        repo.save(employee);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }




}
