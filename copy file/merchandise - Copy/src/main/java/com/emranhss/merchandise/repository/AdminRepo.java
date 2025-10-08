package com.emranhss.merchandise.repository;

import com.emranhss.merchandise.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {




    Optional<Admin> findByUserId(int userId);

    Optional<Admin> findByEmail(String email);

}
