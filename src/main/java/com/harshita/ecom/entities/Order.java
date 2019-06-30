package com.harshita.ecom.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name="custorder")
public class Order {

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public Order() {
		
	}
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="my_seq_gen")
	 Integer id;
	
	@Column
	 String quantity;
	
	 @ManyToOne
	 @JsonIgnoreProperties("orders")
	 @JoinColumn(name="cust_id")
	 Customer customer;
	 
	 @ManyToMany
	 @JoinTable(
			 name = "custorder_product", joinColumns = {@JoinColumn (name ="order_id")},
			 inverseJoinColumns = {@JoinColumn (name = "product_id")}
			 )
	 List<Product> products;
}
