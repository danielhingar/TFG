package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.domain.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

}
