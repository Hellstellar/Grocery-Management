package com.hellstellar.retailG_estimate_service.models;

import java.util.List;

public class EstimateResponse {

	private List<ProductPrice> productPriceList;
	private Integer totalEstimate;
	private String txt_token;
	private String event;
	
	
	public EstimateResponse() {
		
	}
	
	public EstimateResponse(List<ProductPrice> productPriceList, Integer totalEstimate, String event, String txt_token) {
		super();
		this.productPriceList = productPriceList;
		this.totalEstimate = totalEstimate;
		this.event = event;
		this.txt_token = txt_token;
	}
	
	public List<ProductPrice> getProductPriceList() {
		return productPriceList;
	}
	public void setProductPriceList(List<ProductPrice> productPriceList) {
		this.productPriceList = productPriceList;
	}
	public Integer getTotalEstimate() {
		return totalEstimate;
	}
	public void setTotalEstimate(Integer totalEstimate) {
		this.totalEstimate = totalEstimate;
	}

	public String getTxt_token() {
		return txt_token;
	}

	public void setTxt_token(String txt_token) {
		this.txt_token = txt_token;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
	
	
}
