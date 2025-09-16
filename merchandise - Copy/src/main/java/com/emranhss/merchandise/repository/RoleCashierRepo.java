package com.emranhss.merchandise.repository;

import com.emranhss.merchandise.entity.RoleAdmin;
import com.emranhss.merchandise.entity.RoleCashier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleCashierRepo extends JpaRepository<RoleCashier, Long> {


    Optional<RoleCashier> findByUserId(int userId);

    Optional<RoleCashier> findByEmail(String email);

}
