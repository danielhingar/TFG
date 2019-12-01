package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.project.domain.Shipping;
import com.project.repositories.ShippingRepository;

@Service
@Transactional
public class ShippingService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private ShippingRepository shippingRepository;

	// Services----------------------------------------------------------------------------------------------------

	// CRUD--------------------------------------------------------------------------------------------------------

	@Transactional(readOnly = true)
	public List<Shipping> findAll() {
		return (List<Shipping>) shippingRepository.findAll();
	}

	// ----------------------------------------Show------------------------------------------------------
	@Transactional(readOnly = true)
	public Shipping findById(Long id) {
		return shippingRepository.findById(id).orElse(null);

	}

}