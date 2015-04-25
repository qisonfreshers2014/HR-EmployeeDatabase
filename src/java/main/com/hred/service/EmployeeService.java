package com.hred.service;

import java.util.List;

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
import com.hred.exception.EmployeeException;
import com.hred.exception.EncryptionException;
import com.hred.exception.ObjectNotFoundException;
import com.hred.handler.EmployeeHandler;
import com.hred.handler.user.AuthenticationHandlerFactory;
import com.hred.model.Employee;
import com.hred.model.User;
import com.hred.model.user.AuthenticationInput;
import com.hred.model.user.AuthenticationOutput;
import com.hred.service.annotations.RestService;
import com.hred.service.annotations.ServiceStatus;
import com.hred.service.annotations.UnSecure;
import com.hred.service.common.WebserviceRequest;
import com.hred.service.descriptors.outputDescriptors.EmployeeListOutputDescriptors;
 

/**
 * @author 
 *
 */
@Path("/employee")
public class EmployeeService extends BaseService {
	/**
	 * @param headers
	 * @param uriInfo
	 * @param request
	 * @return String
	 * @throws ObjectNotFoundException
	 * @throws BusinessException
	 * @throws EncryptionException
	 */
	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addemployee")
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
	@Path("/save")
	@UnSecure
	public String save(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			WebserviceRequest request) throws ObjectNotFoundException,
			BusinessException, EncryptionException {

		Employee employee = (Employee) JsonUtil.getObject(request.getPayload(),
				Employee.class);

		Employee output = (Employee) EmployeeHandler.getInstance().save(
				employee);

		return JsonUtil.getJsonBasedOnDescriptor(output, User.class);
	}

	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/test")
	@UnSecure
	public String test(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			WebserviceRequest request) throws ObjectNotFoundException,
			BusinessException, EncryptionException {

		Employee employee = (Employee) JsonUtil.getObject(request.getPayload(),
				Employee.class);

		String outputString = "{\"status\": \"SUCCESS\", \"payload\": \"Test Successful\"}";
		return outputString;
	}
	

	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getFilterEmployees")
	@UnSecure
	public String getFilterEmployeeDetails(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			WebserviceRequest request) throws ObjectNotFoundException,
			BusinessException, EncryptionException, EmployeeException {	

		Employee employee = (Employee) JsonUtil.getObject(request.getPayload(),
				Employee.class);
		
		List<Employee> employees = EmployeeHandler.getInstance().getFilterEmployeeDetails(employee);
		System.out.println("COunt : "+ employees.size());
		//String outputString = "{\"status\": \"SUCCESS\", \"payload\": \"Test Successful\"}";
		return JsonUtil.getJsonForListBasedOnDescriptor(employees, Employee.class, EmployeeListOutputDescriptors.class);
		
	}
}

