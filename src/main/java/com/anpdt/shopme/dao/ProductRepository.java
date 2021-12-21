package com.anpdt.shopme.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anpdt.shopme.entity.Cart;
import com.anpdt.shopme.entity.Customer;
import com.anpdt.shopme.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}
