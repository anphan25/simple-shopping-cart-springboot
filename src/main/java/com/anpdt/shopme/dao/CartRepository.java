package com.anpdt.shopme.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anpdt.shopme.entity.Cart;
import com.anpdt.shopme.entity.Customer;
import com.anpdt.shopme.entity.Product;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	public List<Cart> findByCustomer(Customer customer);
	
	public Cart findByCustomerAndProduct(Customer customer,Product product);
	
}
