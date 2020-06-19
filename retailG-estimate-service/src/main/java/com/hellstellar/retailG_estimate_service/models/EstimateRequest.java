package com.hellstellar.retailG_estimate_service.models;

import java.util.List;

public class EstimateRequest {
	private String txn_token;
	private List<ProductQuantity> products;
	private Integer orderId;
	
	public EstimateRequest() {
		
	}
	
	public EstimateRequest(String txn_token, Integer orderId, List<ProductQuantity> products) {
		super();
		this.txn_token = txn_token;
		this.products = products;
		this.orderId = orderId;
	}
	
	public String getTxn_token() {
		return txn_token;
	}
	public void setTxn_token(String txn_token) {
		this.txn_token = txn_token;
	}
	public List<ProductQuantity> getProducts() {
		return products;
	}
	public void setProducts(List<ProductQuantity> products) {
		this.products = products;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	
}
