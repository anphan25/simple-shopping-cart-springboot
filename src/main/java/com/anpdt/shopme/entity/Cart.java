package com.anpdt.shopme.entity;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cart")
	private int idCart;
	
	@Column(name="quantity")
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="id_customer")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="id_product")
	private Product product;
	
	public Cart() {
	}

	public Cart(int quantity, Customer customer, Product product) {
		this.quantity = quantity;
		this.customer = customer;
		this.product = product;
	}

	public int getIdCart() {
		return idCart;
	}

	public void setIdCart(int idCart) {
		this.idCart = idCart;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Transient
	public double getSubTotal() {
		return this.product.getPrice() * this.quantity;
	}
	
}
