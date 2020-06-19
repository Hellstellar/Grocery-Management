package com.hellstellar.retailG_commons.txn_helper;

import io.jsonwebtoken.security.Keys;

public class JWT_Secret {
	private static final String SECRET = "m1cwK_ZxGWC-nQpGvtw6F-tlngE-Q_brNu4Bn7crwKc retail-g service auth ";

	public static String getSecret() {
		return SECRET;
	}
	
}
