package com.emranhss.projectrahmat.repository;

import com.emranhss.projectrahmat.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountry extends JpaRepository<Country, Long> {



}
