package com.emranhss.projectrahmat.repository;

import com.emranhss.projectrahmat.entity.Division;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDivisionRepo extends JpaRepository<Division, Integer> {
}
