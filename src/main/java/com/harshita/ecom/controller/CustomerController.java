package com.harshita.ecom.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harshita.ecom.entities.Customer;
import com.harshita.ecom.entities.Order;
import com.harshita.ecom.entities.Product;
import com.harshita.ecom.repository.CustomerRepository;
import com.harshita.ecom.repository.OrderRepository;
import com.harshita.ecom.repository.ProductRepository;
import com.harshita.ecom.services.CustomerService;
@CrossOrigin("*")
@RestController
@RequestMapping("api")
public class CustomerController {

	@Autowired
	CustomerService custSer;

	@Autowired
	OrderRepository orderRepo;
	@Autowired
	ProductRepository prodRepo;
	@PersistenceContext
	EntityManager em;
	public CustomerController(){
		
		// TODO Auto-generated constructor stub
	}
	@PostMapping("/customer")
		public Customer createCustomer(@RequestBody Customer customer) {
		return custSer.createCustomer(customer);
		
	
	}
	@Transactional
	//  /customer/{id}/order  --post
	@PostMapping("/customer/{id}/order")
		public Order createOrder(@RequestBody Order order, @PathVariable int id) throws Exception {
		order.setCustomer(em.find(Customer.class, id));
		//order.setCustomer(custRepo.findById(id).orElseThrow(() -> new Exception ()));
			return em.merge(order);//(orderRepo.save(order));
			
		}
		
	@PostMapping("/product")
	public Product createProduct(@RequestBody Product prod) {
		return(prodRepo.save(prod));
	}
	@GetMapping("/product")
	public List<Product> getProducts(){
		return (prodRepo.findAll());
	}
	
	@GetMapping("/customers/{id}")
	public Customer getCustomer(@PathVariable int id) {
		
		return (custSer.getCustomer(id));
		}
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		return(custSer.getAllCustomers());
	}
	@GetMapping("/order")
	public List<Order> getOrder(){
		return(orderRepo.findAll());
		
	}
	
}