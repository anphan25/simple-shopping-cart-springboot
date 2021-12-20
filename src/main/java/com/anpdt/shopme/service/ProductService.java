package com.anpdt.shopme.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anpdt.shopme.entity.Product;


public interface ProductService {
	
	public List<Product> getProducts();
	
	public void addToCart(int productId);
}
