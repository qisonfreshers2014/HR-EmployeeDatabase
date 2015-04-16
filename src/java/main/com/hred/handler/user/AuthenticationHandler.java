package com.hred.handler.user;

import com.hred.exception.BusinessException;
import com.hred.exception.EncryptionException;
import com.hred.exception.ObjectNotFoundException;
import com.hred.model.user.AuthenticationInput;
import com.hred.model.user.AuthenticationOutput;

/**
 * @author RAMMOHAN
 * 
 */
public interface AuthenticationHandler {
	public AuthenticationOutput authenticate(AuthenticationInput input)
			throws ObjectNotFoundException, BusinessException, EncryptionException;
}
