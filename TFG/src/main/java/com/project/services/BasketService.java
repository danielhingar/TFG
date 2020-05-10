package com.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Basket;
import com.project.domain.Client;
import com.project.domain.ItemBasket;
import com.project.domain.Product;
import com.project.repositories.BasketRepository;

@Service
@Transactional
public class BasketService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private BasketRepository basketRepository;

	// Services-------------------------------------------------------------------------------------------------
	@Autowired
	private ProductService productService;

	@Autowired
	private ItemBasketService itemBasketService;

	@Autowired
	private ClientService clientService;

	// ---------------------------------Methods---------------------------------------------------------

	// -----------------------------------Show --------------------------------
	@Transactional(readOnly = true)
	public Basket findByClient(String username) {
		Client c = clientService.findByUsername(username);
		
		return c.getBasket();

	}

	// -----------------------------------------Update basket with a new
	// product----------------------------------------------------------
	@Transactional
	public Basket save(Basket basket, int productId, String size, String capacity) {
		List<ItemBasket> items = basket.getItemBaskets();
		Product p = this.productService.findById(productId);
		List<Integer> ids = new ArrayList<>();
		for (int i = 0; i < items.size(); i++) {
			ids.add(items.get(i).getProduct().getId());
		}
		if (!ids.contains(productId)) {
			ItemBasket newItem = new ItemBasket();
			newItem.setProduct(p);
			newItem.setQuantity(1);
			newItem.setSize(size);
			newItem.setCapacity(capacity); 
			items.add(newItem);
			this.itemBasketService.save(newItem);
		} else if (ids.contains(productId) && (!capacity.equals("null"))
				&& (size.equals("null"))) {
			if (addProductCapacity(basket, productId, capacity) != null) {
				items.add(addProductCapacity(basket, productId, capacity));
			}

		} else if (ids.contains(productId) && (!size.equals("null"))
				&& (capacity.equals("null"))) {
			if (addProduct(basket, productId, size) != null) {
				items.add(addProduct(basket, productId, size));
			}

		} else {
			incrementarCantidad(basket, productId, size, capacity);
		}
		return basketRepository.save(basket);
	}

	public void incrementarCantidad(Basket basket, int productId, String size, String capacity) {
		List<ItemBasket> items = basket.getItemBaskets();
		Product p = this.productService.findById(productId);
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getProduct().getId() == p.getId() && size.equals("null") && capacity.equals("null")) {
				items.get(i).setQuantity(items.get(i).getQuantity() + 1);

			}
		}
	}

	public ItemBasket addProduct(Basket basket, int productId, String size) {
		ItemBasket newItem = null;
		List<ItemBasket> items = basket.getItemBaskets();
		List<String> sizes = new ArrayList<>();
		Product p = this.productService.findById(productId);
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getProduct().getId() == p.getId() && !size.equals("null")) {
				sizes.add(items.get(i).getSize());
			}
			if (items.get(i).getProduct().getId() == p.getId() && !size.equals("null") && !sizes.contains(size)) {
				newItem = new ItemBasket();
				newItem.setProduct(p);
				newItem.setQuantity(1);
				newItem.setSize(size);
				newItem.setCapacity("null");
			} else if (items.get(i).getProduct().getId() == p.getId() && !size.equals("null") && sizes.contains(size)
					&& size.equals(items.get(i).getSize())) {
				items.get(i).setQuantity(items.get(i).getQuantity() + 1);
				newItem = null;
			} 
		}
		return newItem;
	}

	public ItemBasket addProductCapacity(Basket basket, int productId, String capacity) {
		ItemBasket newItem = null;
		List<ItemBasket> items = basket.getItemBaskets();
		List<String> capacities = new ArrayList<>();
		Product p = this.productService.findById(productId);
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getProduct().getId() == p.getId() && !capacity.equals("null")) {
				capacities.add(items.get(i).getCapacity());
			}
			if (items.get(i).getProduct().getId() == p.getId() && !capacity.equals("null")
					&& !capacities.contains(capacity)) {
				newItem = new ItemBasket();
				newItem.setProduct(p);
				newItem.setQuantity(1);
				newItem.setCapacity(capacity);
				newItem.setSize("null");
			} else if (items.get(i).getProduct().getId() == p.getId() && !capacity.equals("null")
					&& capacities.contains(capacity) && capacity.equals(items.get(i).getCapacity())) {
				items.get(i).setQuantity(items.get(i).getQuantity() + 1);
				newItem = null;
			}
		}
		return newItem;
	}

	// ----------------------------------------Delete---------------------------
	@Transactional
	public void delete(int id) {
		this.basketRepository.deleteById(id);
	}

	// ----------------------------------------Delete---------------------------
	@Transactional
	public Basket save(Basket basket) {
		return this.basketRepository.save(basket);
	}

}
