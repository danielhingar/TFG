package com.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Basket;
import com.project.domain.Client;
import com.project.domain.Company;
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
		Basket b = c.getBasket();
		return b;

	}

	// -----------------------------------------Update basket with a new
	// product----------------------------------------------------------
	@Transactional
	public Basket save(Basket basket, int productId, String size) {
		List<ItemBasket> items = basket.getItemBaskets();
		Product p = this.productService.findById(productId);
		List<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < items.size(); i++) {
			ids.add(items.get(i).getProduct().getId());
		}
		if (!ids.contains(productId)) {
			ItemBasket newItem = new ItemBasket();
			newItem.setProduct(p);
			newItem.setQuantity(1);
			newItem.setSize(size);
			items.add(newItem);
			this.itemBasketService.save(newItem);
		} else if(ids.contains(productId) && !size.equals(null) && !size.equals("null")) {
			if(addProduct(basket, productId, size)!=null) {
			 items.add(addProduct(basket, productId, size));
			}
		}else {
			incrementarCantidad(basket, productId,size);
		}
		return basketRepository.save(basket);
	}

	public void incrementarCantidad(Basket basket, int productId,String size) {
		List<ItemBasket> items = basket.getItemBaskets();
		Product p = this.productService.findById(productId);
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getProduct().getId() == p.getId() && size.equals("null")) {
				items.get(i).setQuantity(items.get(i).getQuantity() + 1);
				
			}
		}
	}

	public ItemBasket addProduct(Basket basket, int productId,String size) {
		ItemBasket newItem = null;
		List<ItemBasket> items = basket.getItemBaskets();
		List<String> sizes= new ArrayList<>();
		Product p = this.productService.findById(productId);
		for (int i = 0; i < items.size(); i++) {
			if(items.get(i).getProduct().getId() == p.getId() &&  !size.equals("null")) {
				sizes.add(items.get(i).getSize());
			}
			if (items.get(i).getProduct().getId() == p.getId() &&  !size.equals("null") && !sizes.contains(size)) {
				newItem = new ItemBasket();
				newItem.setProduct(p);
				newItem.setQuantity(1);
				newItem.setSize(size);
			}else if(items.get(i).getProduct().getId() == p.getId() &&  !size.equals("null") && size.contains(size) && size.equals(items.get(i).getSize())){
				items.get(i).setQuantity(items.get(i).getQuantity()+1);
				newItem=null;
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
