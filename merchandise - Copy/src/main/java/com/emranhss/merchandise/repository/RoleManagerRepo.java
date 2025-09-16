package com.emranhss.merchandise.repository;

import com.emranhss.merchandise.entity.RoleAdmin;
import com.emranhss.merchandise.entity.RoleManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleManagerRepo extends JpaRepository<RoleManager,Long> {


    Optional<RoleManager> findByUserId(int userId);

    Optional<RoleManager> findByEmail(String email);


}
