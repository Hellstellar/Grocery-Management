package com.hellstellar.recieveorderservice.helpers;

public class JwtConfig {
	private static final String issuer = "RetailG";
	private static final String subject = "VALID";
	private static final Long ttl = 120000l;
	
	public static String getIssuer() {
		return issuer;
	}
	public static String getSubject() {
		return subject;
	}
	public static Long getTtl() {
		return ttl;
	}
	
	
}
