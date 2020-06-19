package com.hellstellar.recieveorderservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hellstellar.recieveorderservice.helpers.JwtConfig;
import com.hellstellar.recieveorderservice.models.OrderList;
import com.hellstellar.recieveorderservice.models.Product;
import com.hellstellar.recieveorderservice.models.ProductQuantity;
import com.hellstellar.recieveorderservice.models.RecieveOrderResponse;
import com.hellstellar.recieveorderservice.repository.OrderListRepository;
import com.hellstellar.retailG_commons.txn_helper.TxnToken;
@Service
public class OrderListService {
	
	@Autowired
	OrderListRepository orderlistrepository;
	
	@Autowired
	ProductService productservice;
	
	@Autowired
	ExpiredTokenService expiredtokenservice;
	
	public ResponseEntity<RecieveOrderResponse> validateAndSave(List<ProductQuantity> productQuantities, String token) {
		List<Product> products = new ArrayList<Product>();
		
		boolean isNotProduct = productQuantities.stream().anyMatch(productQuantity -> {
			Optional<Product> product = productservice.getProductById(productQuantity.getProductId());
			if(product.isPresent() && (product.get().getQuantity() > 0 && product.get().getQuantity() >= productQuantity.getQuantity())) {
				product.get().setQuantity(product.get().getQuantity() - productQuantity.getQuantity());
				productservice.updateQuantity(product.get());
				products.add(product.get());
				return false;
			}
			else 
				return true;
		});
		
		if(isNotProduct) {
			return new ResponseEntity<>(new RecieveOrderResponse(null, "Product does not exist or out of stock", null), HttpStatus.BAD_REQUEST);
		}
		OrderList newOrder = orderlistrepository.save(new OrderList(products));
		expiredtokenservice.saveExpiredToken(token);
		String newToken = TxnToken.createJWT(newOrder.getId().toString(), JwtConfig.getIssuer(), JwtConfig.getSubject(), JwtConfig.getTtl());
		return new ResponseEntity<>(new RecieveOrderResponse(newOrder, "Order Success", newToken), HttpStatus.OK);
	}
	
	public ResponseEntity<List<OrderList>> getAllOrderList() {
		return new ResponseEntity<>(orderlistrepository.findAll(), HttpStatus.OK);
	}
	
}
