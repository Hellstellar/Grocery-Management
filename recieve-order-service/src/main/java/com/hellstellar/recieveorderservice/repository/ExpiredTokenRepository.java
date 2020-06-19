package com.hellstellar.recieveorderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hellstellar.recieveorderservice.models.ExpiredToken;

public interface ExpiredTokenRepository extends JpaRepository<ExpiredToken, String>{

}
