package com.hellstellar.recieveorderservice.models;

import java.util.List;

public class OrderRequest {
	private String txn_token;
	private List<ProductQuantity> products;
	
	public OrderRequest() {
		
	}
	
	public OrderRequest(String txn_token, List<ProductQuantity> products) {
		super();
		this.txn_token = txn_token;
		this.products = products;
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
	
	
}
