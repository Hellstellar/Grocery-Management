package com.hellstellar.retailG_estimate_service.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import com.hellstellar.retailG_commons.txn_helper.TxnToken;
import com.hellstellar.retailG_estimate_service.helpers.JwtConfig;
import com.hellstellar.retailG_estimate_service.models.EstimateResponse;
import com.hellstellar.retailG_estimate_service.models.ProductPrice;
import com.hellstellar.retailG_estimate_service.models.ProductQuantity;
import com.hellstellar.retailG_estimate_service.repositories.ProductPriceRepository;

@Service
public class ProductPriceService {
	
	private final int updateInterval = 120000;
	
	@Autowired
	ProductPriceRepository productpricerepository;
	

	@Autowired
	ExpiredTokenService expiredtokenservice;
	
	@Cacheable("productprice")
	public Integer getEstimateById(Integer productId) {
		Optional<ProductPrice> productPrice = productpricerepository.findById(productId);
		if(productPrice.isPresent()) 
			return productPrice.get().getPrice();
		return null;
	}
	
	
	public ResponseEntity<EstimateResponse> getFinalEstimate(String orderId, List<ProductQuantity> products, String token) {
		List<ProductPrice> productPriceList = new ArrayList<>();
		Integer total = 0;
		for(ProductQuantity product : products) {
			Integer price = getEstimateById(product.getProductId()) * product.getQuantity();
			productPriceList.add(new ProductPrice(product.getProductId(), price));
			total += price;
		}
		expiredtokenservice.saveExpiredToken(token);
		String newToken = TxnToken.createJWT(orderId, JwtConfig.getIssuer(), JwtConfig.getSubject(), JwtConfig.getTtl());
		return new ResponseEntity<>(new EstimateResponse(productPriceList, total, "Estimate Available", newToken), HttpStatus.OK);
	}
	
	public Integer getCurrentPrice(Integer productId) {
		Faker faker = new Faker();
		Optional<ProductPrice> productPrice = productpricerepository.findById(productId);
		if(productPrice.isPresent()) {
			Integer price = productPrice.get().getPrice();
			return faker.number().numberBetween(price - 10, price + 10);
			
		}
		return faker.number().numberBetween(100, 700); 
	}
	
	
	public ProductPrice addProductPrice(Integer productId) {
		return productpricerepository.save(new ProductPrice(productId, getCurrentPrice(productId)));
	}
	
	public void updatePrices() {
		 final long timeInterval = updateInterval;
		  Runnable runnable = new Runnable() {
		  public void run() {
		    while (true) {
		      productpricerepository.findAll().stream().forEach(product -> {
		    	  //We can url to the API to get new price by product id
		    	  Integer newPrice = getCurrentPrice(product.getProduct_id());
		    	  productpricerepository.save(new ProductPrice(product.getProduct_id(), newPrice));
		      });
		      try {
		       Thread.sleep(timeInterval);
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
		      }
		    }
		  };
		  Thread thread = new Thread(runnable);
		  thread.start();
	}
	
}
