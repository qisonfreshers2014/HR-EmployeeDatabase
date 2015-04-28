package com.hred.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import com.hred.service.descriptors.input.EmployeeOutput;
import com.hred.service.descriptors.output.ActivitiesBirthDayOutputDescriptor;

/**
 * @author Venkatesh Chitla
 *
 */
@Path("/employee/")
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
	@Path("/save")
	@UnSecure
	public String save(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			WebserviceRequest request) throws ObjectNotFoundException,
			BusinessException, EncryptionException {

		Employee employee = (Employee) JsonUtil.getObject(request.getPayload(),
				Employee.class);

		Employee output = (Employee) EmployeeHandler.getInstance().save(
				employee);

		return JsonUtil.getJsonBasedOnDescriptor(output, Employee.class);
	}
	
	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/updateEmployee")
	@UnSecure
	public String updateEmployee(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			WebserviceRequest request) throws ObjectNotFoundException,
			BusinessException, EncryptionException {

		Employee employee = (Employee) JsonUtil.getObject(request.getPayload(),
				Employee.class);

		Employee output = (Employee) EmployeeHandler.getInstance().updateEmployee(
				employee);

		return JsonUtil.getJsonBasedOnDescriptor(output, Employee.class);
	}
	
	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/hrupdateEmployee")
	@UnSecure
	public String hrupdateEmployee(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			WebserviceRequest request) throws ObjectNotFoundException,
			BusinessException, EncryptionException {

		Employee employee = (Employee) JsonUtil.getObject(request.getPayload(),
				Employee.class);

		Employee output = (Employee) EmployeeHandler.getInstance().hrUpdateEmployee(
				employee);

		return JsonUtil.getJsonBasedOnDescriptor(output, Employee.class);
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
	@Path("/getEmployees")
	@UnSecure
	public String getEmployees(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {
		List<Employee> employees = EmployeeHandler.getInstance().getEmployees();

		String outputString = "{\"status\": \"SUCCESS\", \"payload\": \"Test Successful\"}";
		return outputString;
	}

	
	
	@POST
	@RestService(input = String.class, output = Employee.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getEmployee")
	@UnSecure
	public String getEmployeeById(
			@Context HttpHeaders headers, @Context UriInfo uriInfo,
			WebserviceRequest request) throws ObjectNotFoundException,
			BusinessException, EncryptionException {
		Employee employee = (Employee) JsonUtil.getObject(request.getPayload(),
				Employee.class);
		Employee output = (Employee) EmployeeHandler.getInstance().getEmployeeById(
				 employee.getId());
		return JsonUtil.getJsonBasedOnDescriptor(output, Employee.class);
		//return JsonUtil.getJsonForListBasedOnDescriptor(employee,
				//Employee.class, Employee.class);
	}
	

	

	@GET
	@RestService(input = String.class, output = ActivitiesBirthDayOutputDescriptor.class)
	@ServiceStatus(value = "complete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/getTodayBirthday")
	@UnSecure
	public String getTodayBirthday(
			
			@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws BusinessException {
		List<Employee> getTodayBirthdays = EmployeeHandler.getInstance()
				.getTodayBirthday();
		return JsonUtil.getJsonForListBasedOnDescriptor(getTodayBirthdays,
				Employee.class, ActivitiesBirthDayOutputDescriptor.class);
	}

}
