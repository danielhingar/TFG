package com.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Basket;
import com.project.repositories.BasketRepository;

@Service
@Transactional
public class BasketService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private BasketRepository basketRepository;

	// ---------------------------------Methods---------------------------------------------------------

	// -----------------------------------Show --------------------------------
	@Transactional(readOnly = true)
	public Basket findById(int id) {
		return basketRepository.findById(id).orElse(null);

	}

	// ----------------------------------------Delete---------------------------
	@Transactional
	public void delete(int id) {
		this.basketRepository.deleteById(id);
	}

}
