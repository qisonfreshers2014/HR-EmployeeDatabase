package com.hred.service;

import javax.ws.rs.CookieParam;

/**
 * Base Service for all the RESTFUL Services
 * @author Vinay Thandra
 *
 */
public class BaseService {

	public static final String SESSION_TOKEN_NAME = "hredSessionToken";
	
	@CookieParam(SESSION_TOKEN_NAME)
	String hredSessionToken;
	

	/*
	 * Get the session id for the user session
	 */

    public BaseService() {
    }

    public String getSessionId() {
		return hredSessionToken;
	}
	
	

}
