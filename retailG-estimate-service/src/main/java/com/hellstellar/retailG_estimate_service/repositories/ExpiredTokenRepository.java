package com.hellstellar.retailG_estimate_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hellstellar.retailG_estimate_service.models.ExpiredToken;



public interface ExpiredTokenRepository extends JpaRepository<ExpiredToken, String>{

}
