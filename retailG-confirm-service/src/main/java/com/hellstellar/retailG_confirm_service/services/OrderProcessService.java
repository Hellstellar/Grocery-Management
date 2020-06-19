package com.hellstellar.retailG_confirm_service.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hellstellar.retailG_confirm_service.models.ConfirmResponse;
import com.hellstellar.retailG_confirm_service.models.EstimateResponse;
import com.hellstellar.retailG_confirm_service.models.ProductQuantity;
import com.hellstellar.retailG_confirm_service.models.RecieveOrderResponse;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
@Service
public class OrderProcessService {
	
	public HttpEntity<String> createRecieveRequest(String txnToken, List<ProductQuantity> products) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    JSONObject orderObject = new JSONObject();
	    
	    orderObject.put("txn_token", txnToken);
	    orderObject.put("products", products);
	    return new HttpEntity<String>(orderObject.toString(), headers);
	}
	
	public HttpEntity<String> createEstimateRequest(String txnToken, Integer orderId, List<ProductQuantity> products) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    JSONObject orderObject = new JSONObject();	    
	    orderObject.put("txn_token", txnToken);
	    orderObject.put("orderId", orderId);
	    orderObject.put("products", products);
	    return new HttpEntity<String>(orderObject.toString(), headers);
	}
	
	public ResponseEntity<ConfirmResponse> createConfirmResponse(RecieveOrderResponse recieveResponse, EstimateResponse estimateResponse) {
		recieveResponse.getOrderlist().getProducts().forEach(product -> {
			Integer price = estimateResponse.getProductPriceList().stream().filter( 
						productPrice -> productPrice.getProduct_id() == product.getProductId()
					).findFirst().get().getPrice();
			product.setPrice(price);
		});
		ConfirmResponse response = new ConfirmResponse(recieveResponse.getOrderlist(), "Order confirmed", estimateResponse.getTotalEstimate(), estimateResponse.getTxt_token());
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
}
