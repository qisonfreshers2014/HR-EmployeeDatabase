package com.hred.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.mail.EmailException;

import com.hred.common.json.JsonUtil;
import com.hred.exception.BusinessException;
import com.hred.exception.EncryptionException;
import com.hred.exception.ObjectNotFoundException;
import com.hred.handler.EmployeeHandler;
import com.hred.handler.SendNotificationHistoryHandler;
import com.hred.handler.user.AuthenticationHandlerFactory;
import com.hred.model.Employee;
import com.hred.model.SendNotificationHistory;
import com.hred.model.user.AuthenticationInput;
import com.hred.model.user.AuthenticationOutput;
import com.hred.service.annotations.RestService;
import com.hred.service.annotations.ServiceStatus;
import com.hred.service.annotations.UnSecure;
import com.hred.service.common.WebserviceRequest;
import com.hred.service.descriptors.output.DisplayNotificationHome;
import com.hred.service.descriptors.output.SentMail;

@Path("/v1/SendNotificationHistory")
public class SendNotificationHistoryService extends BaseService {

	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/save")
	@UnSecure
	public String save(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {
		
		SendNotificationHistory entry = (SendNotificationHistory) JsonUtil.getObject(request.getPayload(),
				SendNotificationHistory.class);

		SendNotificationHistory output=(SendNotificationHistory) SendNotificationHistoryHandler.getInstance().save(entry);
		
		return JsonUtil.getJsonBasedOnDescriptor(output,SendNotificationHistory.class);
	}
	
	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add")
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
	
	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/test")
	@UnSecure
	public String test(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {
		
		SendNotificationHistory SendNotificationHistory = (SendNotificationHistory) JsonUtil.getObject(
				request.getPayload(), SendNotificationHistory.class);

		String outputString = "{\"status\": \"SUCCESS\", \"payload\": \"Test Successful\"}";
		return outputString;
	}
	
	
	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/sendMail")
	@UnSecure
	public String sendMail(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException, EmailException {	
		
		DisplayNotificationHome sentMailToEmployee= (DisplayNotificationHome) JsonUtil.getObject(
				request.getPayload(), DisplayNotificationHome.class);
		String output = SendNotificationHistoryHandler.getInstance().SentMail(sentMailToEmployee);
		
		
		return "{\"status\": \"SUCCESS\", \"payload\": \"Test Successful\"}";
	}
	
	
	
		
}
