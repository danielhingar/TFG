package com.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.ItemBasket;
import com.project.repositories.ItemBasketRepository;

@Service
@Transactional
public class ItemBasketService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private ItemBasketRepository itemBasketRepository;

	// Services-------------------------------------------------------------------------------------------------
	@Autowired
	private ProductService productService;

	// ---------------------------------Methods---------------------------------------------------------

	// -----------------------------------Show --------------------------------
	@Transactional(readOnly = true)
	public ItemBasket findById(int id) {
		return itemBasketRepository.findById(id).orElse(null);

	}

	// -----------------------------------------Save----------------------------------------------------------
	@Transactional
	public ItemBasket save(ItemBasket itemBasket, int productId) {
		itemBasket.setProduct(this.productService.findById(productId));
		itemBasket.setQuantity(1);
		return itemBasketRepository.save(itemBasket);
	}

	// -----------------------------------------Save----------------------------------------------------------
	@Transactional
	public ItemBasket save(ItemBasket itemBasket) {
		return itemBasketRepository.save(itemBasket);
	}

	// ----------------------------------------Delete----------------------------------------------------
	@Transactional
	public void delete(int id) {
		this.itemBasketRepository.deleteById(id);
	}

}
