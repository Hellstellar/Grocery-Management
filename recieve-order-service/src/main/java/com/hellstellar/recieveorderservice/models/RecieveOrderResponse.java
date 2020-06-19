package com.hellstellar.recieveorderservice.models;

public class RecieveOrderResponse {
	
	private OrderList orderlist;
	private String event;
	private String txn_token;
	
	public RecieveOrderResponse() {
		
	}
	
	
	
	public RecieveOrderResponse(OrderList orderlist, String event, String txn_token) {
		super();
		this.orderlist = orderlist;
		this.event = event;
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



	public String getTxn_token() {
		return txn_token;
	}



	public void setTxn_token(String txn_token) {
		this.txn_token = txn_token;
	}
	
	
}
