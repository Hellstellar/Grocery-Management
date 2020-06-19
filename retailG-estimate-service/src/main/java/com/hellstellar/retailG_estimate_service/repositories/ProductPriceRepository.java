package com.hellstellar.retailG_estimate_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hellstellar.retailG_estimate_service.models.ProductPrice;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer>{

}
