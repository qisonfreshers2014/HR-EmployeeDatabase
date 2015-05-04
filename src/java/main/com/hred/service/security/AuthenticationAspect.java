package com.hred.service.security;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hred.common.cache.Cache;
import com.hred.common.cache.CacheManager;
import com.hred.common.cache.CacheRegionType;
import com.hred.common.json.JsonUtil;
import com.hred.common.logger.Logger;
import com.hred.common.security.RequestId;
import com.hred.exception.BusinessException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.ObjectNotFoundException;
import com.hred.exception.SystemException;
import com.hred.model.UserSessionToken;
import com.hred.model.user.AuthenticationInput;
import com.hred.model.user.DefaultAuthenticationInput;
import com.hred.persistence.session.SessionFactoryUtil;
import com.hred.service.BaseService;
import com.hred.service.annotations.UnSecure;
import com.hred.service.common.ServiceRequestContext;
import com.hred.service.common.ServiceRequestContextHolder;
import com.hred.service.common.ServiceResponse;
import com.hred.service.common.WebserviceRequest;
import com.hred.service.common.WebserviceSecurityObject;

/**
 * @author Vamsi Kuchi
 */
@Aspect
public class AuthenticationAspect {
	Logger logger = Logger.getLogger("infodumps");
	org.apache.log4j.Logger loggerL;

	@Around("execution(* com.hred.service.*.*(..))"
			+ " && !execution(* com.hred.service.*.getSessionId(..))"
			+ " && !cflowbelow(execution(* com.hred.service.*.*(..)))")
	public Object doAccessCheck(ProceedingJoinPoint thisJoinPoint) {
		// Object key = null;
		Transaction tx = null;
		try {
			authenticateUser(thisJoinPoint);
			RequestId id = this.createRequestId();
			ServiceRequestContextHolder.getContext().setRequestId(
					this.createRequestId());
			SessionFactoryUtil sessionUtil = SessionFactoryUtil.getInstance();
			Session session=sessionUtil.getCurrentSession();
			tx = SessionFactoryUtil.getInstance().beginTransaction(session);
			ServiceRequestContextHolder.getContext().setDbSession(session);
			Object ob = thisJoinPoint.proceed();
			tx.commit();

			return ob;
		} catch (Throwable ex) {
			logger.error(ex.toString(), ex);
			ex.printStackTrace();
			try {
				if (null != tx) {
					tx.rollback();
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}

			// ex.printStackTrace();
			return ServiceResponse.getFailureResponseString(ex);
		} finally {
			ServiceRequestContextHolder.setContext(null);
		}
	}



	private String getSessionId(ProceedingJoinPoint thisJoinPoint)
			throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		String sessionId = null;

		sessionId = getURIParameterValue(thisJoinPoint,
				BaseService.SESSION_TOKEN_NAME);

		// get user session id from header
		if (sessionId == null) {
			sessionId = getHttpHeaderValue(thisJoinPoint,
					BaseService.SESSION_TOKEN_NAME);
			if (sessionId != null) {
				sessionId = getDecodeValue(sessionId);
			}
		}

		if (sessionId == null) {
			// get session id from cookie
			Method sessionMethod = thisJoinPoint.getTarget().getClass()
					.getMethod("getSessionId");
			Object sessId = sessionMethod.invoke(thisJoinPoint.getTarget());
			if (sessId != null) {
				sessionId = (String) sessId;
			}
			if (sessionId != null) {
				sessionId = getDecodeValue(sessionId);
			}
		}

		if (sessionId == null) {
			// Try get from WebServiceRequest
			sessionId = this.getWebServiceSecurityValue(thisJoinPoint,
					WebserviceSecurityObject.SESSION_TOKEN_NAME);

		}

		return sessionId;
	}

	public static ServiceRequestContext getSystemContext() {
		ServiceRequestContext ctx = ServiceRequestContextHolder.getContext();
		UserSessionToken userSessionToken = new UserSessionToken();
		ctx.setUserSessionToken(userSessionToken);
		return ctx;
	}

	/**
	 * Gets the header value. This looks for the header arguments in the method
	 * and identifies the header value from header argument.
	 * 
	 * @param headerName
	 * @return value for the header or null
	 */
	private String getHttpHeaderValue(JoinPoint jp, String headerName) {
		Object[] args = jp.getArgs();
		Class[] types = ((CodeSignature) jp.getSignature()).getParameterTypes();
		HttpHeaders headers = null;

		for (int i = 0; i < args.length; i++) {
			if (types[i] == HttpHeaders.class) {
				headers = (HttpHeaders) args[i];
			}
		}

		List<String> valueList = headers.getRequestHeader(headerName);

		if (valueList != null && valueList.size() > 0) {
			return valueList.get(0);
		}

		return null;

	}

	/**
	 * Gets the URIInfo value. This looks for the header arguments in the method
	 * and identifies the header value from header argument.
	 * 
	 * @param
	 * @return value for the header or null
	 */
	private String getURIParameterValue(JoinPoint jp, String parameterName) {
		Object[] args = jp.getArgs();
		Class[] types = ((CodeSignature) jp.getSignature()).getParameterTypes();
		UriInfo uriInfo = null;

		for (int i = 0; i < args.length; i++) {
			if (types[i] == UriInfo.class) {
				uriInfo = (UriInfo) args[i];
			}
		}

		MultivaluedMap<String, String> paramters = uriInfo.getQueryParameters();

		String value = paramters.getFirst(parameterName);

		return value;
	}

	/**
	 * Gets the WebServiceSecurity value. This looks for the header arguments in
	 * the method and identifies the header value from header argument.
	 * 
	 * @param
	 * @return value for the header or null
	 */
	private String getWebServiceSecurityValue(JoinPoint jp, String securityName) {
		Object[] args = jp.getArgs();
		Class[] types = ((CodeSignature) jp.getSignature()).getParameterTypes();
		WebserviceRequest webServiceRequest = null;
		String value = null;

		for (int i = 0; i < args.length; i++) {
			if (types[i] == WebserviceRequest.class) {
				webServiceRequest = (WebserviceRequest) args[i];
			}
		}
		if (webServiceRequest == null) { // story id 17396335
			return null;
		}

		WebserviceSecurityObject securityObject = webServiceRequest
				.getSecurityObject();

		// Method[] methods = WebserviceSecurityObject.class.getMethods();

		String methodName = "get" + securityName.substring(0, 1).toUpperCase()
				+ securityName.substring(1);
		Method method = null;
		try {
			method = WebserviceSecurityObject.class.getMethod(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (securityObject != null && method != null) {
			try {
				value = (String) method.invoke(securityObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return value;
	}

	/**
	 *
	 */
	private String getDecodeValue(String value) {
		String decodeValue = null;
		try {
			if (value != null) {
				decodeValue = URLDecoder.decode(value, "UTF-8");
			}
		} catch (Exception e) {
		}
		return decodeValue;
	}

	/**
	 * Checks if the API is a secure call or not
	 * 
	 * @return true f secured else retun false. default all are secured unless
	 *         method is annotated with UnSecure annotation.
	 */
	private boolean isSecureCall(ProceedingJoinPoint thisJoinPoint) {
		MethodSignature signature = (MethodSignature) thisJoinPoint
				.getSignature();
		return signature.getMethod().getAnnotation(UnSecure.class) == null;
	}

	private RequestId createRequestId() {
		return new RequestId();
	}

	private String getHostUrl(JoinPoint jp) {
		Object[] args = jp.getArgs();
		Class[] types = ((CodeSignature) jp.getSignature()).getParameterTypes();
		UriInfo uriInfo = null;
		for (int i = 0; i < args.length; i++) {
			if (types[i] == UriInfo.class) {
				uriInfo = (UriInfo) args[i];
			}
		}
		URI path = uriInfo.getAbsolutePath();
		String host = uriInfo.getAbsolutePath().getHost();
		return host;
	}

	private String getEmailFromPayload(JoinPoint jp) {
		Object[] args = jp.getArgs();
		Class[] types = ((CodeSignature) jp.getSignature()).getParameterTypes();
		WebserviceRequest webServiceRequest = null;
		String value = null;

		for (int i = 0; i < args.length; i++) {
			if (types[i] == WebserviceRequest.class) {
				webServiceRequest = (WebserviceRequest) args[i];
			}
		}
		if (webServiceRequest == null) { // story id 17396335
			return null;
		}

		Object payload = webServiceRequest.getPayload();
		DefaultAuthenticationInput input = (DefaultAuthenticationInput) JsonUtil
				.getObject(payload, AuthenticationInput.class);
		return input.getEmail();
	}

	private void authenticateUser(ProceedingJoinPoint thisJoinPoint)
			throws ObjectNotFoundException, BusinessException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if(isSecureCall(thisJoinPoint)) {
			String sessionId = getSessionId(thisJoinPoint);

			if (sessionId == null){
				throw new SystemException(ExceptionCodes.USER_NOT_AUTHENTICATED, ExceptionMessages.USER_NOT_AUTHENTICATED);
			}

			//check if session is valid or not
			UserSessionToken userSessionToken = (UserSessionToken) CacheManager.getInstance().getCache(CacheRegionType.USER_SESSION_CACHE).getValue(sessionId);
			if(userSessionToken == null){
				throw new SystemException(ExceptionCodes.USER_NOT_AUTHENTICATED, ExceptionMessages.USER_NOT_AUTHENTICATED);
			} else {
				ServiceRequestContext ctx = ServiceRequestContextHolder.getContext();
				ctx.setUserSessionToken(userSessionToken);
			}
		} else { // insecure api so user may not be logged in here so dont throw exception if he/she is not logged in
			String sessionId = getSessionId(thisJoinPoint);

			if (sessionId == null){
				// this will help us to set the correct creator/modifier id for the operations performed in insecure apis 
				//and to avoid NPE in case anybody tries to use contents e.g. userId, userEmail etc. from UserSessionToken whereas it is null.
				ServiceRequestContextHolder.setContext(getSystemContext());
			}
			else {
				//check if session is valid or not
				UserSessionToken userSessionToken = (UserSessionToken) CacheManager.getInstance().getCache(CacheRegionType.USER_SESSION_CACHE).getValue(sessionId);
				if(userSessionToken == null){
					ServiceRequestContextHolder.setContext(getSystemContext());
				} else {
					ServiceRequestContext ctx = ServiceRequestContextHolder.getContext();
					ctx.setUserSessionToken(userSessionToken);
				}
			}
		}

	}

	private String getHttpHeaderValues(JoinPoint jp) {
		Object[] args = jp.getArgs();
		Class[] types = ((CodeSignature) jp.getSignature()).getParameterTypes();
		HttpHeaders headers = null;
		for (int i = 0; i < args.length; i++) {
			if (types[i] == HttpHeaders.class) {
				headers = (HttpHeaders) args[i];
			}
		}
		Map<String, List<String>> map = headers.getRequestHeaders();
		/*
		 * String url = map.get("Referer").toString(); final int endIndex =
		 * url.indexOf("?"); if (endIndex > -1) url = url.substring(0,
		 * endIndex); //"http://localhost:8080/affinities/affinity1/ int
		 * indexOfHash = url.indexOf("#"); if (indexOfHash != -1) { url =
		 * url.substring(0, indexOfHash); } url = url.replace("[", ""); url =
		 * url.replace("]", "");
		 */
		return "";
	}

}
