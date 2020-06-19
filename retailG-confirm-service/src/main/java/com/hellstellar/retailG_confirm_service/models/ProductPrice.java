package com.hellstellar.retailG_confirm_service.models;

public class ProductPrice {
	
	Integer product_id;
	
	Integer price;

	public ProductPrice() {
		
	}
	
	public ProductPrice(int product_id, Integer price) {
		super();
		this.product_id = product_id;
		this.price = price;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
}
