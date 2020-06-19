package com.hellstellar.retailG_commons.txn_helper;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;
public class TxnToken {

	public static String createJWT(String id, String issuer, String subject, long ttlMillis) {
		
		
	    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	 
	    long nowMillis = System.currentTimeMillis();
	    Date now = new Date(nowMillis);
	 
	    
	    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(JWT_Secret.getSecret());
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	 
	    
	    JwtBuilder builder = Jwts.builder().setId(id)
	                                .setIssuedAt(now)
	                                .setSubject(subject)
	                                .setIssuer(issuer)
	                                .signWith(signatureAlgorithm, signingKey);
	 
	    if (ttlMillis >= 0) {
	    long expMillis = nowMillis + ttlMillis;
	        Date exp = new Date(expMillis);
	        builder.setExpiration(exp);
	    }
	 
	    return builder.compact();
	}
	
	public static boolean isExpired(String jwt) {
		try {
			Jwts.parser()         
				       .setSigningKey(DatatypeConverter.parseBase64Binary(JWT_Secret.getSecret()))
				       .parseClaimsJws(jwt).getBody();
			return false;
		}
		catch(JwtException e) {
			return true;
		}
	}
	
	
	public static boolean isValidJWT(String jwt, String phase) {
		 
	    //This line will throw an exception if it is not a signed JWS (as expected)
		try {
			Claims claims = Jwts.parser()         
	       .setSigningKey(DatatypeConverter.parseBase64Binary(JWT_Secret.getSecret()))
	       .parseClaimsJws(jwt).getBody();
			if(claims.getSubject().equals(phase)) {
				return true;
			}
			return false;
		}
		catch(JwtException e) {
			return false;
		}
	}
	
}