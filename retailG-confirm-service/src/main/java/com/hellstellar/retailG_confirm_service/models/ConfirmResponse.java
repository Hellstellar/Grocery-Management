package com.hellstellar.retailG_confirm_service.models;

public class ConfirmResponse {
	
	private OrderList orderlist;
	private String event;
	private Integer estimate;
	//Intruction on order collection
	private String txn_token;
	
	public ConfirmResponse() {
		
	}
	
	public ConfirmResponse(String event) {
		super();
		this.event = event;
	}

	public ConfirmResponse(OrderList orderlist, String event, Integer estimate, String txn_token) {
		super();
		this.orderlist = orderlist;
		this.event = event;
		this.estimate = estimate;
		this.txn_token = txn_token;
	}

	public OrderList getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(OrderList orderlist) {
		this.orderlist = orderlist;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Integer getEstimate() {
		return estimate;
	}

	public void setEstimate(Integer estimate) {
		this.estimate = estimate;
	}

	public String getTxn_token() {
		return txn_token;
	}

	public void setTxn_token(String txn_token) {
		this.txn_token = txn_token;
	}
	
	
}
