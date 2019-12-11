package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.domain.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

}
