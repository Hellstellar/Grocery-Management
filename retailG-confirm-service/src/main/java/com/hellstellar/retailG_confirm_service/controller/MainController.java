package com.hellstellar.retailG_confirm_service.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.hellstellar.retailG_commons.txn_helper.TxnToken;
import com.hellstellar.retailG_confirm_service.helpers.JwtConfig;
import com.hellstellar.retailG_confirm_service.models.ConfirmResponse;
import com.hellstellar.retailG_confirm_service.models.EstimateResponse;
import com.hellstellar.retailG_confirm_service.models.ProductQuantity;
import com.hellstellar.retailG_confirm_service.models.RecieveOrderResponse;
import com.hellstellar.retailG_confirm_service.services.OrderProcessService;


@RestController
public class MainController {

	@Autowired
	RestTemplate resttemplate;
	
	@Autowired
	OrderProcessService orderprocess_service;
	
	@RequestMapping(value = "/orderlist", method = RequestMethod.POST)
	public ResponseEntity<ConfirmResponse> OrderProcess(@RequestBody List<ProductQuantity> products) {
		String txnToken = TxnToken.createJWT(JwtConfig.getId(), JwtConfig.getIssuer(), JwtConfig.getSubject(), JwtConfig.getTtl());
		HttpEntity<String> orderRequest = orderprocess_service.createRecieveRequest(txnToken, products);
		try {
			 RecieveOrderResponse recieveOrderResponse = resttemplate.postForObject("http://recieve-order-service/orderlist", orderRequest, RecieveOrderResponse.class);
			 
			 HttpEntity<String> estimateRequest = orderprocess_service.createEstimateRequest(recieveOrderResponse.getTxn_token(), recieveOrderResponse.getOrderlist().getId(), products);
			 EstimateResponse estimateResponse = resttemplate.postForObject("http://retailG-estimate-service/estimate", estimateRequest, EstimateResponse.class);
			 
			 return orderprocess_service.createConfirmResponse(recieveOrderResponse, estimateResponse);
		} 
		catch(HttpClientErrorException e) {
			String body = e.getResponseBodyAsString();
			JSONObject json = new JSONObject(body);
			return new ResponseEntity<>(new ConfirmResponse(json.getString("event")), e.getStatusCode());
		}
	}
}
