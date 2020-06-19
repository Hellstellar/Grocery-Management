package com.hellstellar.retailG_confirm_service.helpers;

public class JwtConfig {
	private static final String id = "Pseudo";
	private static final String issuer = "RetailG";
	private static final String subject = "INIT";
	private static final Long ttl = 120000l;
	
	public static String getId() {
		return id;
	}
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
