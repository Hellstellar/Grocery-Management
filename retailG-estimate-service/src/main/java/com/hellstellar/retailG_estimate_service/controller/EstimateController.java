package com.hellstellar.retailG_estimate_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hellstellar.retailG_commons.txn_helper.TxnToken;
import com.hellstellar.retailG_estimate_service.models.EstimateRequest;
import com.hellstellar.retailG_estimate_service.models.EstimateResponse;
import com.hellstellar.retailG_estimate_service.services.ExpiredTokenService;
import com.hellstellar.retailG_estimate_service.services.ProductPriceService;

@RestController
public class EstimateController {

	
	private static final String phase = "VALID";
	
	@Autowired
	ProductPriceService productpriceservice;
	
	@Autowired
	ExpiredTokenService expiredtokenservice;
	
	@RequestMapping(value="/estimate", method=RequestMethod.POST)
	public ResponseEntity<EstimateResponse> estimatePriceList(@RequestBody EstimateRequest request) {
		
		String token = request.getTxn_token();
		if(TxnToken.isValidJWT(token, phase) && !expiredtokenservice.isExpiredToken(token)) {
				return productpriceservice.getFinalEstimate(request.getOrderId().toString(), request.getProducts(), request.getTxn_token());
		}
		return new ResponseEntity<>(new EstimateResponse(null, null, "Transaction Token invalid", null), HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/price-test-data", method=RequestMethod.POST)
	public Boolean addTestPrice(@RequestBody Integer productId) {
		if(productpriceservice.addProductPrice(productId) != null)
			return true;
		return false;
	}
	
}
