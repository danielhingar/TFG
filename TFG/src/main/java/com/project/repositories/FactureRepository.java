package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.domain.Facture;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {

}
