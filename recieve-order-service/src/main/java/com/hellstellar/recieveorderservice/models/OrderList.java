package com.hellstellar.recieveorderservice.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class OrderList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_product", joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName = "product_id")})
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
