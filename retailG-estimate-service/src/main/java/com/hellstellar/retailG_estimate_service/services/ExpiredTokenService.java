package com.hellstellar.retailG_estimate_service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hellstellar.retailG_commons.txn_helper.TxnToken;
import com.hellstellar.retailG_estimate_service.models.ExpiredToken;
import com.hellstellar.retailG_estimate_service.repositories.ExpiredTokenRepository;

@Service
public class ExpiredTokenService {

	private final int checkInterval = 60000;
	
	@Autowired
	ExpiredTokenRepository expiredtokenrepository;
	
	public ExpiredToken saveExpiredToken(String token) {
		return expiredtokenrepository.save(new ExpiredToken(token)); 
	}
		
	public boolean isExpiredToken(String token) {
		return expiredtokenrepository.existsById(token);
	}
	
	public void deleteExpiredToken() {
		 final long timeInterval = checkInterval;
		  Runnable runnable = new Runnable() {
		  public void run() {
		    while (true) {
		      expiredtokenrepository.findAll().stream().forEach(token -> {
		    	  String fetchedToken = token.getExpiredToken();
		    	  if(TxnToken.isExpired(fetchedToken))
			    	  expiredtokenrepository.deleteById(fetchedToken);
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
