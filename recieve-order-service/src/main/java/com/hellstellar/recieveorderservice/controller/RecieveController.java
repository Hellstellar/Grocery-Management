package com.hellstellar.recieveorderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hellstellar.recieveorderservice.models.OrderList;
import com.hellstellar.recieveorderservice.models.OrderRequest;
import com.hellstellar.recieveorderservice.models.Product;
import com.hellstellar.recieveorderservice.models.RecieveOrderResponse;
import com.hellstellar.recieveorderservice.service.ExpiredTokenService;
import com.hellstellar.recieveorderservice.service.OrderListService;
import com.hellstellar.recieveorderservice.service.ProductService;
import com.hellstellar.retailG_commons.txn_helper.TxnToken;

@RestController
public class RecieveController {
	
	private static final String phase = "INIT";
	
	@Autowired
	ProductService productservice;
	
	@Autowired
	OrderListService orderlistservice;
	
	@Autowired 
	ExpiredTokenService expiredtokenservice;
	
	@RequestMapping(value = "/orderlist", method = RequestMethod.GET)
	public ResponseEntity<List<OrderList>> getOrderLists() {
		return orderlistservice.getAllOrderList();
	}
	@RequestMapping(value = "/orderlist", method = RequestMethod.POST)
	public ResponseEntity<RecieveOrderResponse> validateOrderList(@RequestBody OrderRequest request) {
		String token = request.getTxn_token();
		if(TxnToken.isValidJWT(token, phase) && !expiredtokenservice.isExpiredToken(token)) 
			return orderlistservice.validateAndSave(request.getProducts(), request.getTxn_token());
		return new ResponseEntity<>(new RecieveOrderResponse(null, "Transaction Token invalid", null), HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProducts() {
		return productservice.getAllProducts();
	}
	
	@RequestMapping(value = "/product", method = RequestMethod.POST )
	public Product saveProduct(@RequestBody Product product) {
		return productservice.save(product);
	}
	
	@RequestMapping(value = "/product-test-data", method = RequestMethod.GET)
	public String addTestProducts() {
		return productservice.saveTest();
	}
	
}
