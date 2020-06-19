package com.hellstellar.retailG_confirm_service.models;



public class Product {
	
	
	public Product() {
		
	}
	
	
	public Product(Integer productId, String name, String color, String size) {
		super();
		this.productId = productId;
		this.name = name;
		this.color = color;
		this.size = size;
	}
	
	
	private Integer productId;
	private String name;
	private String color;
	private String size;
	private Integer price;
	
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


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
}
