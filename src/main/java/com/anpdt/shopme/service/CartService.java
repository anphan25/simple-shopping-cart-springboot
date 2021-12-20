package com.anpdt.shopme.service;

import java.util.List;

import com.anpdt.shopme.entity.Cart;
import com.anpdt.shopme.entity.Customer;

public interface CartService {
	public List<Cart> getCart(Customer customer);
	
	public boolean addToCart(int productId, int quantity, Customer customer);
	
	public void removeItem(int productId, Customer customer);
	
	public void checkout(Customer customer);
}
