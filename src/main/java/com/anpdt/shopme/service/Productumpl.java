package com.anpdt.shopme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anpdt.shopme.dao.ProductRepository;
import com.anpdt.shopme.entity.Product;

@Service
public class Productumpl implements ProductService {

	@Autowired
	public ProductRepository productRepo;
	
	@Override
	public List<Product> getProducts() {
		return productRepo.findAll();
	}

	@Override
	public void addToCart(int productId) {
		
		
	}

}
