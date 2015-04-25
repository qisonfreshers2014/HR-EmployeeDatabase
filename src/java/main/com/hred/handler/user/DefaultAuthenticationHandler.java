package com.hred.handler.user;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.TimeZone;

import com.hred.common.EncryptionFactory;
import com.hred.common.Utils;
import com.hred.common.cache.Cache;
import com.hred.common.cache.CacheManager;
import com.hred.common.cache.CacheRegionType;
import com.hred.exception.BusinessException;
import com.hred.exception.EncryptionException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.ObjectNotFoundException;
import com.hred.exception.SystemException;
import com.hred.model.User;
import com.hred.model.UserSessionToken;
import com.hred.model.user.AuthenticationInput;
import com.hred.model.user.AuthenticationOutput;
import com.hred.model.user.DefaultAuthenticationInput;
import com.hred.persistence.dao.DAOFactory;

/**
 * @author RAMMOHAN
 * 
 */
public class DefaultAuthenticationHandler implements AuthenticationHandler {

	@Override
	public AuthenticationOutput authenticate(AuthenticationInput input)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {

		DefaultAuthenticationInput daInput = (DefaultAuthenticationInput) input;
		int authStatus = User.AUTH_STATUS_NONE;
		String email = daInput.getEmail();
		String password = daInput.getPassword();
		if (email != null) {
			email = email.trim().toLowerCase();
		}
		if (password != null) {
			password = password.trim();
		}
		User employee = null;
		
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		// TODO handle email doesn't exists case.
		employee = daoFactory.getUserDAO().getUserByEmail(email);
		//String encryptedPassword = Utils.encrypt(password);
		String passwordFromDB = employee.getPassword();
		
		
		/////////////////////////////////////////////////////////////////////////////////
		long userId = employee.getId();

		
		
	////////////////////////////////////////////////////////////
		String encryptedPassword=Utils.encrypt(password.trim());
		boolean userValidity = passwordFromDB.equals(encryptedPassword);
		
		if (!userValidity) {
			throw new BusinessException(ExceptionCodes.INVALID_PASSWORD,

					ExceptionMessages.INVALID_PASSWORD);
		}

		authStatus = User.AUTH_STATUS_EXISTING;

		String sessionToken = null;
		try {
			TimeZone.setDefault(TimeZone.getDefault());
			sessionToken = URLEncoder
					.encode(EncryptionFactory.getEncryption(true).encrypt(
							employee.getEmail() + Calendar.getInstance().getTimeInMillis()),
							"UTF-8");
		} catch (EncryptionException ee) {
			throw new SystemException(ExceptionCodes.INTERNAL_ERROR,
					ExceptionMessages.INTERNAL_ERROR);
		} catch (UnsupportedEncodingException uee) {
			throw new SystemException(ExceptionCodes.INTERNAL_ERROR,
					ExceptionMessages.INTERNAL_ERROR);
		}

		UserSessionToken userSessionToken = new UserSessionToken();
		userSessionToken.setUserEmail(employee.getEmail());
		userSessionToken.setUserId(employee.getId());
		userSessionToken.setUserSessionId(sessionToken);
		//userSessionToken.setRoleId(employee.());  //set the role here
		//////////////////////////////////////////

		////////////////////////////////////////////
		Cache cache = CacheManager.getInstance().getCache(CacheRegionType.USER_SESSION_CACHE);
		cache.put(sessionToken, userSessionToken);
		System.out.println("Session Token : "+sessionToken);		
		System.out.println("Cached : "+cache.getValue(sessionToken));
		AuthenticationOutput authenticationOutput = new AuthenticationOutput(sessionToken, authStatus, employee);
		return authenticationOutput;
	}

}
