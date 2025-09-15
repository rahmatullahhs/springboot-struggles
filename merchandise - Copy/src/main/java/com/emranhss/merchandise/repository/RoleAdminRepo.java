package com.emranhss.merchandise.repository;

import com.emranhss.merchandise.entity.RoleAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleAdminRepo extends JpaRepository<RoleAdmin, Long> {


    Optional<RoleAdmin> findByUserId(int userId);

    Optional<RoleAdmin> findByEmail(String email);
}
