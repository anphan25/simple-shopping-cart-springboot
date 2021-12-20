package com.anpdt.shopme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anpdt.shopme.dao.CartRepository;
import com.anpdt.shopme.dao.ProductRepository;
import com.anpdt.shopme.entity.Cart;
import com.anpdt.shopme.entity.Customer;
import com.anpdt.shopme.entity.Product;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private ProductRepository prodRepo;

	@Override
	public List<Cart> getCart(Customer cus) {
		return cartRepo.findByCustomer(cus);
	}

	@Override
	public boolean addToCart(int productId, int quantity, Customer customer) {

		Product product = prodRepo.findById(productId).get();

		Cart cart = cartRepo.findByCustomerAndProduct(customer, product);

		int afterBuying = product.getQuantityInStock() - quantity;

		if (afterBuying < 0) {
			return false;
		} else {
			if (cart != null) {
				
				cart.setQuantity(cart.getQuantity() + quantity);

			} else {
				cart = new Cart(quantity, customer, product);
			}
			
			product.setQuantityInStock(afterBuying);
			
			cartRepo.save(cart);
		}
		
		return true;
	}

	@Override
	public void removeItem(int productId, Customer customer) {
		
		Product product = prodRepo.getById(productId);
		
		Cart cart = cartRepo.findByCustomerAndProduct(customer, product);
		
		cartRepo.delete(cart);
		
	}

	@Override
	public void checkout(Customer customer) {
		
		List<Cart> cartItems = cartRepo.findByCustomer(customer);
		System.out.println(cartItems);
		
		for(Cart cart : cartItems) {
			System.out.println(cart);
			cartRepo.delete(cart);
		}
		
	}

}
