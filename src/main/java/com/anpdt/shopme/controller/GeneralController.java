package com.anpdt.shopme.controller;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anpdt.shopme.entity.Cart;
import com.anpdt.shopme.entity.Customer;
import com.anpdt.shopme.entity.Product;
import com.anpdt.shopme.service.CartService;
import com.anpdt.shopme.service.ProductService;

@Controller
public class GeneralController {

	@Autowired
	private ProductService product;

	@Autowired
	private CartService cart;

	@GetMapping("/showCart")
	public String showCart(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();

		Customer customer = (Customer) session.getAttribute("currentUser");

		List<Cart> cartItems = cart.getCart(customer);

		double total = 0;

		for (Cart cart : cartItems) {
			total = total + cart.getSubTotal();
		}

		model.addAttribute("total", total);

		model.addAttribute("cart_items", cartItems);

		return "cart";
	}

	@GetMapping("/showProduct")
	public String showProduct(Model model, HttpServletRequest request) {

		List<Product> products = product.getProducts();

		Customer customer = new Customer(10, "Messi", "mess@gmail.com");

		Customer customer2 = new Customer(1, "Leslie", "leslie@luv2code.com");

		HttpSession session = request.getSession();

		session.setAttribute("currentUser", customer2);
		model.addAttribute("products", products);

		return "product-list";
	}

	@PostMapping("/addToCard")
	public String addToCart(@RequestParam("productId") int productId, @RequestParam("quantity") int quantity,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();

		Customer customer = (Customer) session.getAttribute("currentUser");

		boolean result = cart.addToCart(productId, quantity, customer);

		System.out.println(result);

		if (result == false) {
			model.addAttribute("buyError", "error");
		}

		return "redirect:/showProduct";
	}

	@GetMapping("/removeItem")
	public String removeItem(@RequestParam("productId") int productId, HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();

		Customer customer = (Customer) session.getAttribute("currentUser");
		
		cart.removeItem(productId, customer);

		return "redirect:/showCart";
	}

}
