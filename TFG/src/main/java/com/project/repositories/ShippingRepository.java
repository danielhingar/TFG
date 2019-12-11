package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.domain.Shipping;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping, Integer> {

}
