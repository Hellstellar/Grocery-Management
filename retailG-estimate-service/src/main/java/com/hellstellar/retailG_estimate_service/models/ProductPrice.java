package com.hellstellar.retailG_estimate_service.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductPrice {
	
	@Id
	Integer product_id;
	
	@Column(name="price")
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
