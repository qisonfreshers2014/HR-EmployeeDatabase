package com.hred.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.hred.common.json.JsonUtil;
import com.hred.exception.BusinessException;
import com.hred.exception.EncryptionException;
import com.hred.exception.ObjectNotFoundException;
import com.hred.handler.user.AuthenticationHandlerFactory;
import com.hred.model.user.AuthenticationInput;
import com.hred.model.user.AuthenticationOutput;
import com.hred.service.annotations.RestService;
import com.hred.service.annotations.ServiceStatus;
import com.hred.service.annotations.UnSecure;
import com.hred.service.common.ServiceRequestContextHolder;
import com.hred.service.common.WebserviceRequest;
@Path("/v1/employee/")
public class EmployeeService {

	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/authenticate")
	@UnSecure
	public String authenticate(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {
		
		AuthenticationInput input = (AuthenticationInput) JsonUtil.getObject(
				request.getPayload(), AuthenticationInput.class);

		AuthenticationOutput output = AuthenticationHandlerFactory
				.getAuthenticationHandler(input.getAuthType()).authenticate(
						input);
		
		return JsonUtil.getJsonBasedOnDescriptor(output,
				AuthenticationOutput.class);
	}
	
	public String getSessionId(){
		return "ABC";
	}
	
}
