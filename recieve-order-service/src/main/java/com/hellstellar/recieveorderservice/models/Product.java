package com.hellstellar.recieveorderservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	
	
	public Product() {
		
	}
	
	
	public Product(Integer productId, String name, String color, String size, Integer quantity) {
		super();
		this.productId = productId;
		this.name = name;
		this.color = color;
		this.size = size;
		this.quantity = quantity;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private Integer productId;
	@Column(name = "name")
	private String name;
	@Column(name = "color")
	private String color;
	@Column(name = "size")
	private String size;
	@Column(name = "quantity")
	private Integer quantity;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
