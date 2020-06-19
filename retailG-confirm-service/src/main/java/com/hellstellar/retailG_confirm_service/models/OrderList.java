package com.hellstellar.retailG_confirm_service.models;

import java.util.List;




public class OrderList {


	private Integer id;
	private List<Product> products;
	
	public OrderList() {
		
	}

	public OrderList(List<Product> products) {
		super();
		this.products = products;
	}
	
	public OrderList(Integer id, List<Product> products) {
		super();
		this.id = id;
		this.products = products;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
}
