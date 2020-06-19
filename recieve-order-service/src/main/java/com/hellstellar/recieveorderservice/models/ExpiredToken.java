package com.hellstellar.recieveorderservice.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExpiredToken {
	@Id
	String ExpiredToken;
	
	public ExpiredToken() {
		
	}

	public ExpiredToken(String expiredToken) {
		super();
		ExpiredToken = expiredToken;
	}

	public String getExpiredToken() {
		return ExpiredToken;
	}

	public void setExpiredToken(String expiredToken) {
		ExpiredToken = expiredToken;
	}
	
	
}
