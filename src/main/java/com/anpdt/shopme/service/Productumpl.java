package com.anpdt.shopme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	@Override
	public Page<Product> findAll(int pageNumber) {
		
		Sort sort = Sort.by("name").ascending();
		
		Pageable page = PageRequest.of(pageNumber - 1, 5, sort);
				
		return productRepo.findAll(page);
	}

}
