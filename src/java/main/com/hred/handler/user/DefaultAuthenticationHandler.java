package com.hred.handler.user;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Pattern;
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
import com.hred.exception.UserException;
import com.hred.model.Employee;
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
	AuthenticationOutput authenticationOutput=null;

	@Override
	public AuthenticationOutput authenticate(AuthenticationInput input)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {
		
		

		DefaultAuthenticationInput daInput = (DefaultAuthenticationInput) input;
		int authStatus = Employee.AUTH_STATUS_NONE;
		if (isLoginValidated(daInput)){
		String email = daInput.getEmail();
		String password = daInput.getPassword();
	
		Employee emp = null;
		DAOFactory daoFactory = DAOFactory.getInstance();
		// TODO handle email doesn't exists case.
		emp = daoFactory.getEmployeeDAO().getUserByEmail(email);
		//String encryptedPassword = Utils.encrypt(password);
		String emailFromDB = emp.getEmail();
		String passwordFromDB=emp.getPassword();
		
		/////////////////////////////////////////////////////////////////////////////////
		long empId = emp.getId();

		
		
	////////////////////////////////////////////////////////////
		
		boolean emailValidity = emailFromDB.equals(email);
		boolean passwordValidity = passwordFromDB.equals(password);

		if (!emailValidity) {
			throw new BusinessException(ExceptionCodes.INVALID_PASSWORD,

					ExceptionMessages.INVALID_PASSWORD);
		}
		else if (!passwordValidity) {
			throw new BusinessException(ExceptionCodes.INVALID_PASSWORD,

					ExceptionMessages.INVALID_PASSWORD);
		}



		authStatus = Employee.AUTH_STATUS_EXISTING;

		String sessionToken = null;
		try {
			TimeZone.setDefault(TimeZone.getDefault());
			sessionToken = URLEncoder
					.encode(EncryptionFactory.getEncryption(true).encrypt(
							emp.getEmail()
									+ Calendar.getInstance().getTimeInMillis()),
							"UTF-8");
		} catch (EncryptionException ee) {
			throw new SystemException(ExceptionCodes.INTERNAL_ERROR,
					ExceptionMessages.INTERNAL_ERROR);
		} catch (UnsupportedEncodingException uee) {
			throw new SystemException(ExceptionCodes.INTERNAL_ERROR,
					ExceptionMessages.INTERNAL_ERROR);
		}

		UserSessionToken userSessionToken = new UserSessionToken();
		userSessionToken.setUserEmail(emp.getEmail());
		userSessionToken.setUserId(emp.getId());
		userSessionToken.setUserSessionId(sessionToken);
		//////////////////////////////////////////

		////////////////////////////////////////////
		Cache cache = CacheManager.getInstance().getCache(CacheRegionType.USER_SESSION_CACHE);
		cache.put(sessionToken, userSessionToken);
		System.out.println("Session Token : "+sessionToken);		
		System.out.println("Cached : "+cache.getValue(sessionToken));
		AuthenticationOutput authenticationOutput = new AuthenticationOutput(sessionToken, authStatus, emp);
	return authenticationOutput;
	}
		
		return authenticationOutput;
}

	private boolean isLoginValidated(DefaultAuthenticationInput daInput) throws UserException{
		boolean isValidated = false;		
		if (null == daInput) {
			throw new UserException(ExceptionCodes.USER_ID_AND_PASSWORD_NULL,
					ExceptionMessages.USER_ID_AND_PASSWORD_NULL);
		}
		isValidated = validateEmail(daInput.getEmail());
		isValidated = validatePassword(daInput.getPassword());		
		return isValidated;
	}
	private static boolean validateEmail(String email) throws UserException {
		boolean isValidated = false;
		if (email == null || email.isEmpty() || email.trim().isEmpty()) {
			throw new UserException(ExceptionCodes.EMAIL_CANNOT_BE_EMPTY,
					ExceptionMessages.EMAIL_CANNOT_BE_EMPTY);
		}
		boolean isEmailPatternValid = Pattern.compile(Utils.EMAIL_PATTERN)
				.matcher(email).matches();
		isValidated = true;
		if (!isEmailPatternValid) {
			isValidated = false;
			throw new UserException(ExceptionCodes.EMAIL_FORMAT,
					ExceptionMessages.EMAIL_FORMAT);
		}
		
		return isValidated;
	}
	private boolean validatePassword(String password) throws UserException {
		boolean isValidated = false;
		if (password == null || password.trim().isEmpty()) {
			isValidated = false;
			throw new UserException(ExceptionCodes.PASSWORD_NULL,
					ExceptionMessages.PASSWORD_NULL);
		}
		try {
			Utils.validatePassword(password);
			isValidated = true;
		} catch (Exception be) {
			isValidated = false;
			throw new UserException(ExceptionCodes.PASSWORD_FORMAT,
					ExceptionMessages.PASSWORD_FORMAT);
		}
		return isValidated;
	}
}
