package com.project.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Basket;
import com.project.domain.Client;
import com.project.domain.Comment;

import com.project.domain.Product;
import com.project.repositories.ProductRepository;

@Service
@Transactional
public class ProductService {

	// Repository------------------------------------------------------------------------------------------------
	@Autowired
	private ProductRepository productRepository;

	// Services----------------------------------------------------------------------------------------------------
	@Autowired
	private CompanyService companyService;

	@Autowired
	private ClientService clientService;



	@Autowired
	private CommentService commentService;

	// CRUD--------------------------------------------------------------------------------------------------------

	// ----------------------------------------List------------------------------------------------------
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	private static int minimum(int a, int b, int c) {
		return Math.min(a, Math.min(b, c));
	}

	public static int computeLevenshteinDistance(String str1, String str2) {
		return computeLevenshteinDistance(str1.toCharArray(), str2.toCharArray());
	}

	private static int computeLevenshteinDistance(char[] str1, char[] str2) {
		int[][] distance = new int[str1.length + 1][str2.length + 1];

		for (int i = 0; i <= str1.length; i++) {
			distance[i][0] = i;
		}
		for (int j = 0; j <= str2.length; j++) {
			distance[0][j] = j;
		}
		for (int i = 1; i <= str1.length; i++) {
			for (int j = 1; j <= str2.length; j++) {
				distance[i][j] = minimum(distance[i - 1][j] + 1, distance[i][j - 1] + 1,
						distance[i - 1][j - 1] + ((str1[i - 1] == str2[j - 1]) ? 0 : 1));
			}
		}
		return distance[str1.length][str2.length];

	}

	// ----------------------------------Recomendation---------------------------------------------------
	@Transactional(readOnly = true)
	public Set<Product> findRecomendation(int productId) {
		Product product = this.findById(productId);
		Set<Product> recommendation = new HashSet<>();
		String[] cadena = product.getName().split(" ");
		Map<Integer, Product> recommendationProv = new HashMap<Integer, Product>();
		List<Product> all = productRepository.findProductByCategory(product.getCategory());
		all.remove(product);
		Random rdm = new Random();
		Collections.shuffle(all, rdm);
		for (Product p : all) {
			String[] cadena2 = p.getName().split(" ");
			for(int i= 0; i<cadena2.length;i++)
			if (i< cadena2.length && i< cadena.length) {
				if (computeLevenshteinDistance(cadena[i], cadena2[i]) < 4 && cadena2[i].length()>2 && cadena[i].length()>2) {
					recommendationProv.put(computeLevenshteinDistance(cadena[i], cadena2[i]), p);

				}
			} 
			
		}
		ArrayList<Integer> sorted = new ArrayList<Integer>(recommendationProv.keySet());
		Collections.sort(sorted);
		for (Integer x : sorted) {
			recommendation.add(recommendationProv.get(x));
		}
		
		return recommendation;
	}

	// ----------------------------------------Show------------------------------------------------------
	@Transactional(readOnly = true)
	public Product findById(int id) {
		return this.productRepository.findById(id).orElse(null);

	}

	// -----------------------------------------Save----------------------------------------------------------
	@Transactional
	public Product saveProduct(Product product, String username) {
		product.setCompany(this.companyService.findByUsername(username));
		product.setCreateDate(new Date());
		product.setStatus("DISPONIBLE");
		return productRepository.save(product);
	}

	// ----------------------------------------Save
	// update-----------------------------------------------------
	@Transactional
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	// -----------------------------------------Delete----------------------------------------------------------
	@Transactional
	public void delete(int id) {
		Product p = this.findById(id);
		Integer res= 0;
		List<Client> clients = this.clientService.findAll();
		for (Client t : clients) {
			Basket b = t.getBasket();
			for (int i = 0; i < b.getItemBaskets().size(); i++) {
				if (p.getName().equals(b.getItemBaskets().get(i).getProduct().getName())) {
					res= res+1;

				}else {
					res= res+0;
				}

			}
		
		}
		if(res == 1) {
			p.setStatus("ELIMINADO");
			productRepository.save(p);
		}
		if(res == 0) { 
		productRepository.deleteById(id);
		}
	}

	// -----------------------------------------Pagination----------------------------------------------------------
	@Transactional(readOnly = true)
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Page<Product> findAllByCompany(String username, Pageable pageable) {
		return productRepository.findProductByCompany(username, pageable);
	}
	
	@Transactional(readOnly = true)
	public Page<Product> findAllByCompanyClient(String username, Pageable pageable) {
		return productRepository.findProductByCompanyClient(username, pageable);
	}

	// -----Valoration------------------------------------------------------------------------------------------------
	@Transactional(readOnly = true)
	public Integer calculateAvgValoration(int id) {
		Double res = 0.0;
		List<Comment> comments = this.commentService.findCommentByProduct(id);
		for (Comment c : comments) {
			res +=  c.getValoration();
		}
		return (int) Math.round(res/comments.size());
	}

}
