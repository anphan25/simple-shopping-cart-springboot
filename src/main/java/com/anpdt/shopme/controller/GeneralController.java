package com.anpdt.shopme.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/showProduct/{pageNumber}")
	public String showProduct(Model model, HttpServletRequest request, @PathVariable("pageNumber") int currentPage) {

		Page<Product> page = product.findAll(currentPage);

		List<Product> products = page.getContent();

		int totalPages = page.getTotalPages();

		Customer customer = new Customer(10, "Messi", "mess@gmail.com");

		Customer customer2 = new Customer(1, "Leslie", "leslie@luv2code.com");

		HttpSession session = request.getSession();

		session.setAttribute("currentUser", customer);
		session.setAttribute("currenPage", currentPage);
		model.addAttribute("products", products);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("currentPage", currentPage);

		return "product-list";
	}

	@GetMapping("/addToCard")
	public String addToCart(@RequestParam("productId") int productId, @RequestParam("quantity") int quantity,
			HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();

		int currentPage = (int) session.getAttribute("currenPage");

		Customer customer = (Customer) session.getAttribute("currentUser");

		boolean result = cart.addToCart(productId, quantity, customer);

		if (!result) {
			System.err.println("ye");
			model.addAttribute("buy_error", "This item is not enough to you");
		}

		return "forward:/showProduct/" + currentPage;
	}

	@GetMapping("/removeItem")
	public String removeItem(@RequestParam("productId") int productId, HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();

		Customer customer = (Customer) session.getAttribute("currentUser");

		cart.removeItem(productId, customer);

		return "redirect:/showCart";
	}

	@GetMapping("/checkout")
	public String checkout(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();

		Customer customer = (Customer) session.getAttribute("currentUser");

		cart.checkout(customer);

		return "redirect:/showProduct/1";
	}

	@GetMapping("/loadProductManagement")
	public String loadProductManagement(Model model) {

		List<Product> products = product.getProducts();

		model.addAttribute("products", products);

		return "product-management";
	}

	@GetMapping("/showFormForAdd")
	public String showAddForm(Model model) {

		Product product = new Product();

		model.addAttribute("product", product);
		model.addAttribute("add_product", "Add");

		return "product-form";
	}

	@PostMapping("/saveProduct")
	public String saveProduct(@ModelAttribute("product") Product savedProduct) {

		product.save(savedProduct);

		return "redirect:/loadProductManagement";
	}

	@GetMapping("/showFormForUpdate")
	public String showUpdateForm(Model model, @RequestParam("productId") int id) {

		Product pord = product.getProduct(id);

		model.addAttribute("product", pord);

		return "product-form";
	}

	@GetMapping("/deleteProduct")
	public String deleteproduct(Model model, @RequestParam("productId") int id) {

		Product prod = product.getProduct(id);
		product.delete(prod);

		return "redirect:/loadProductManagement";
	}

//	@PostMapping("/testDate")
//	public String testDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate date, Model model ) {
//		
//		System.out.println(date);
//		
//		model.addAttribute("myDate", date);
//		
//		return "redirect:/showProduct/1";
//	}
}
