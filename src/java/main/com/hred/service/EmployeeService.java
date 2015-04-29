/**
 * 
 */
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
import com.hred.exception.EncryptionException;
import com.hred.exception.ObjectNotFoundException;
import com.hred.handler.EmployeeHandler;
import com.hred.model.Employee;
import com.hred.service.annotations.RestService;
import com.hred.service.annotations.ServiceStatus;
import com.hred.service.annotations.UnSecure;
import com.hred.service.common.WebserviceRequest;
import com.hred.service.descriptors.Input.EmployeeSearchInputDescriptor;

/**
 * @author saisudha
 *
 */
@Path("/v1/employee/")

public class EmployeeService extends BaseService {
	
	@POST
	@RestService(input = String.class, output = Employee.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getEmployeeDetails")
	@UnSecure
	public String getEmployee(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			WebserviceRequest request) throws ObjectNotFoundException,
			BusinessException, EncryptionException {		
		Employee employee = (Employee) JsonUtil.getObject(request.getPayload(), Employee.class);
		List<Employee> employees = EmployeeHandler.getInstance().getEmployee();
		System.out.println("Count : "+ employees.size());
		return JsonUtil.getJsonForListBasedOnDescriptor(employees, Employee.class, Employee.class);

	}
	
	
	@POST
	@RestService(input = String.class, output = Employee.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/searchEmployee")
	@UnSecure
	public String searchEmployee(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			WebserviceRequest request) throws ObjectNotFoundException,
			BusinessException, EncryptionException {		
		EmployeeSearchInputDescriptor employee = (EmployeeSearchInputDescriptor) JsonUtil.getObject(request.getPayload(), EmployeeSearchInputDescriptor.class);
		List<Employee> employees = EmployeeHandler.getInstance().searchEmployee(employee);
		System.out.println("Count : "+ employees.size());		
		return JsonUtil.getJsonForListBasedOnDescriptor(employees, EmployeeSearchInputDescriptor.class, Employee.class);

	}

}
