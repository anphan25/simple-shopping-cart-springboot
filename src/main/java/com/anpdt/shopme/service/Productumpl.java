package com.anpdt.shopme.service;

import java.util.List;
import java.util.Optional;

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
		return productRepo.findAllByOrderByName();
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

	@Override
	public void save(Product product) {
		
		productRepo.save(product);
		
	}

	@Override
	public Product getProduct(int id) {
		
		Optional<Product> result = productRepo.findById(id);
		
		Product product = null;
		
		if(!result.isPresent()) {
			throw new RuntimeException("Not found this product");
		}else {
			product = result.get();
		}
		
		return product;
	}

	@Override
	public void delete(Product product) {
		
		productRepo.delete(product);
	}

}
