package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.domain.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {

}
