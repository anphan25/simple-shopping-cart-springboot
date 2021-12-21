package com.anpdt.shopme.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.anpdt.shopme.entity.Product;


public interface ProductService {
	
	public List<Product> getProducts();
	
	public void addToCart(int productId);
	
	public Page<Product >findAll(int pageNumber);
	
	public void save(Product product);
	
	public Product getProduct(int id);
	
	public void delete(Product product);
}
