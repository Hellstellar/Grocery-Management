package com.hellstellar.recieveorderservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.javafaker.Faker;
import com.hellstellar.recieveorderservice.models.Product;
import com.hellstellar.recieveorderservice.repository.ProductRepository;


@Service
public class ProductService {
	
	@Autowired
	ProductRepository productrepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	public Optional<Product> getProductById(Integer id) {
		return productrepository.findById(id);
	}
	
	public ResponseEntity<List<Product>> getAllProducts() {
		return new ResponseEntity<>(productrepository.findAll(), HttpStatus.OK);
	}
	
	public boolean productNotExists(Integer id) {
		return !productrepository.existsById(id);
	}
	
	public Product save(Product product) {
		if(restTemplate.postForObject("http://localhost:9093/price-test-data", product.getProductId(), Boolean.class))
			return productrepository.save(product);
		return null;
	}
	
	public Product updateQuantity(Product product) {
		return productrepository.save(product);
	}
	
	public String saveTest() {
		Faker faker = new Faker();
		List<Product> products = new ArrayList<Product>();
		for(int i = 0; i < 50; i++) {
			if(restTemplate.postForObject("http://localhost:9093/price-test-data", i, Boolean.class))
				products.add(new Product(i, faker.commerce().material(), faker.color().name(), "L", faker.number().numberBetween(2, 100)));
		}
		productrepository.saveAll(products);
		return "Added test data to product table";
	}
}
