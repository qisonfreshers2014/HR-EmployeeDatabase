package com.hred.handler.user;

import com.hred.model.User;

/**
 * @author RAMMOHAN
 * 
 */
public class AuthenticationHandlerFactory {

	public static AuthenticationHandler getAuthenticationHandler(String authType) {

		if (authType.equals(User.AUTH_TYPE_REGULAR)) {
			return new DefaultAuthenticationHandler();
		}
		return new DefaultAuthenticationHandler();
	}
}
