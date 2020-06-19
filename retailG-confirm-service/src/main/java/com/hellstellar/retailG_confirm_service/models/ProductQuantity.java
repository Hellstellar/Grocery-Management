package com.hellstellar.retailG_confirm_service.models;

public class ProductQuantity {

	private Integer productId;
	private Integer quantity;
	
	public ProductQuantity() {
		
	}
	
	public ProductQuantity(Integer productId, Integer quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	
}
