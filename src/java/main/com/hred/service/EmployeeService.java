package com.hred.service;

import java.util.List;
import java.util.Map;

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
import com.hred.model.DesignationHistory;
import com.hred.model.Employee;
import com.hred.model.File;
import com.hred.model.FilterEmployee;
import com.hred.model.user.AuthenticationInput;
import com.hred.model.user.AuthenticationOutput;
import com.hred.pagination.EmployeeListPaginationInput;
import com.hred.pagination.NotificationPaginationInput;
import com.hred.pagination.PaginationOutput;
import com.hred.service.annotations.RestService;
import com.hred.service.annotations.ServiceStatus;
import com.hred.service.annotations.UnSecure;
import com.hred.service.common.ServiceRequestContextHolder;
import com.hred.service.common.WebserviceRequest;
import com.hred.service.descriptors.input.ChangePassword;
import com.hred.service.descriptors.input.EmployeeSearchInputDescriptor;
import com.hred.service.descriptors.input.ProfilePics;
import com.hred.service.descriptors.output.DisplayNotificationHome;
import com.hred.service.descriptors.output.EmployeeListPaginationOutputDescriptor;
import com.hred.service.descriptors.output.NotificationHomeFilterInputDiscriptor;
import com.hred.service.descriptors.outputDescriptors.EmployeeListOutputDescriptors;

/**
 * @author Venkatesh Chitla
 *
 */
@Path("/v1/employee/")
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
	
	//Gets the loggedin user details.
	
	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getLoggedInUserDetails")
	
	public String getLoggedInUserDetails(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			WebserviceRequest request) throws ObjectNotFoundException,
			BusinessException, EncryptionException {

		long userId = ServiceRequestContextHolder.getContext().getUserSessionToken().getUserId();

		 Employee employees = (Employee) EmployeeHandler
				    .getInstance().getLoggedInUser(userId);
		 return JsonUtil.getJsonBasedOnDescriptor(employees, Employee.class);
	}
	
	// To Check weather the user is loggedin or not.
	
	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/isloggedin")
	public String isUserLoggedIn(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request) {
		return JsonUtil.getJsonBasedOnDescriptor(Boolean.TRUE, Boolean.class);
	}
	
     // Saves the employee details.

	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/save")
	public String save(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			WebserviceRequest request) throws ObjectNotFoundException,
			BusinessException, EncryptionException {
		Employee employee = (Employee) JsonUtil.getObject(request.getPayload(),
				Employee.class);

		Employee output = (Employee) EmployeeHandler.getInstance().saveAOP(
				employee);

		return JsonUtil.getJsonBasedOnDescriptor(output, Employee.class);
	}
	
	

	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getEmployees")
	
	public String getEmployees(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {
		FilterEmployee employee = (FilterEmployee) JsonUtil.getObject(
				request.getPayload(), FilterEmployee.class);
		List<Employee> employees = EmployeeHandler.getInstance().getEmployeesAOP(
				employee);
		return JsonUtil.getJsonForListBasedOnDescriptor(employees,
				Employee.class, EmployeeListOutputDescriptors.class);
		// return outputString;
	}

	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/updateEmployee")
	
	public String updateEmployee(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {

		Employee employee = (Employee) JsonUtil.getObject(request.getPayload(),
				Employee.class);

		Employee output = (Employee) EmployeeHandler.getInstance()
				.updateEmployee(employee);

		return JsonUtil.getJsonBasedOnDescriptor(output, Employee.class);
	}
	
	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/hrupdateEmployee")
	public String hrupdateEmployee(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {
		Employee employee = (Employee) JsonUtil.getObject(request.getPayload(),
				Employee.class);
		Employee output = (Employee) EmployeeHandler.getInstance()
				.hrUpdateEmployeeAOP(employee);
		return JsonUtil.getJsonBasedOnDescriptor(output, Employee.class);
	}
	
	//Inactivates the employee.
	
	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/deleteEmployee")
	public String deleteEmployee(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {

		Employee employee = (Employee) JsonUtil.getObject(request.getPayload(),
				Employee.class);

		Employee output = (Employee) EmployeeHandler.getInstance()
				.deleteEmployeeAOP(employee);

		return JsonUtil.getJsonBasedOnDescriptor(output, Employee.class);
	}
	@POST
	@RestService(input = String.class, output = Employee.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getEmployeeDetails")
	public String getEmployee(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {
		Employee employee = (Employee) JsonUtil.getObject(request.getPayload(),
				Employee.class);
		List<Employee> employees = EmployeeHandler.getInstance().getEmployee();
		System.out.println("Count : " + employees.size());
		return JsonUtil.getJsonForListBasedOnDescriptor(employees,Employee.class, Employee.class);
	}
	@POST
	@RestService(input = String.class, output = Employee.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/searchEmployee")
	public String searchEmployee(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {
		EmployeeSearchInputDescriptor employee = (EmployeeSearchInputDescriptor) JsonUtil
				.getObject(request.getPayload(),EmployeeSearchInputDescriptor.class);
		List<Employee> employees = EmployeeHandler.getInstance()
				.searchEmployeeAOP(employee);
		System.out.println("Count : " + employees.size());
		return JsonUtil.getJsonForListBasedOnDescriptor(employees,
				EmployeeSearchInputDescriptor.class, Employee.class);
	}
	@POST
	 @RestService(input = String.class, output = String.class)
	 @ServiceStatus(value = "complete")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("/viewEmployee")
	
	 public String viewEmployee(@Context HttpHeaders headers,
	   @Context UriInfo uriInfo, WebserviceRequest request)
	   throws ObjectNotFoundException, BusinessException,
	   EncryptionException, EmployeeException {

	  Employee employee = (Employee) JsonUtil.getObject(request.getPayload(),
	    Employee.class);
	  Employee employees = (Employee) EmployeeHandler
	    .getInstance().viewEmployee(employee.getEmployeeId());
	  //System.out.println("COunt : " + employees.size());
	  // String outputString =
	  // "{\"status\": \"SUCCESS\", \"payload\": \"Test Successful\"}";
	  return JsonUtil.getJsonBasedOnDescriptor(employees, Employee.class);
	 }
	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/test")
	public String test(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			WebserviceRequest request) throws ObjectNotFoundException,
			BusinessException, EncryptionException {
		String outputString = "{\"status\": \"SUCCESS\", \"payload\": \"Test Successful\"}";
		return outputString;
	}
	@POST
	@RestService(input = String.class, output = Employee.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getEmployee")
	
	public String getEmployeeById(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {
		Employee employee = (Employee) JsonUtil.getObject(request.getPayload(),
				Employee.class);
		Employee output = (Employee) EmployeeHandler.getInstance()
				.getEmployeeById(employee.getEmployeeId());
		return JsonUtil.getJsonBasedOnDescriptor(output, Employee.class);
		// return JsonUtil.getJsonForListBasedOnDescriptor(employee,
		// Employee.class, Employee.class);
	}
	@POST
	@RestService(input = String.class, output = DisplayNotificationHome.class)
	@ServiceStatus(value = "complete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/getAllEvents")
	public String getAllEvents(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws BusinessException {
		List<DisplayNotificationHome> getAllEvents = EmployeeHandler
				.getInstance().getAllEventsAOP();
		return JsonUtil.getJsonForListBasedOnDescriptor(getAllEvents,
				DisplayNotificationHome.class, DisplayNotificationHome.class);
	}
	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getNotificationDisplayCriteria")
	public String getNotificationDisplayCriteria(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {
		NotificationHomeFilterInputDiscriptor filterCriteria = (NotificationHomeFilterInputDiscriptor) JsonUtil
				.getObject(request.getPayload(),
						NotificationHomeFilterInputDiscriptor.class);
		List<DisplayNotificationHome> displayoutput = EmployeeHandler
				.getInstance().getNotificationDisplayCriteriaAOP(filterCriteria);
		return JsonUtil.getJsonForListBasedOnDescriptor(displayoutput,
				DisplayNotificationHome.class, DisplayNotificationHome.class);
	}
	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/changePassword")
	public String changePassword(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request) throws EncryptionException, BusinessException {
		ChangePassword changePasswordEmployee = (ChangePassword) JsonUtil.getObject(request.getPayload(),
				ChangePassword.class);
		EmployeeHandler
		.getInstance().changePassword(changePasswordEmployee);
		return JsonUtil.getJsonBasedOnDescriptor(changePasswordEmployee, ChangePassword.class);
	}
	
	//Gets all the Profile Pics of employees.
	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getProfilePics")
	public String getProfilePics(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request) throws EncryptionException, BusinessException {
		ProfilePics profilePics = (ProfilePics) JsonUtil.getObject(request.getPayload(),ProfilePics.class);
		List<String> profilefilePaths=EmployeeHandler.getInstance().getProfilePics();
		return JsonUtil.getJsonBasedOnDescriptor(profilefilePaths, ProfilePics.class);
	}
	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/logout")
	public String logout(@Context HttpHeaders headers,@Context UriInfo uriInfo, WebserviceRequest request) {
		boolean isLogout = EmployeeHandler.getInstance().logout();
		return JsonUtil.getJsonBasedOnDescriptor(isLogout, Boolean.class);
	}
	
	        //updates designation details	
	
			@POST
			@RestService(input = DesignationHistory.class, output = String.class)
			@ServiceStatus(value = "complete")
			@Consumes(MediaType.APPLICATION_JSON)
			@Produces(MediaType.APPLICATION_JSON)
			@Path("/updateDesignation")
			public String updateDesigantionDetails(@Context HttpHeaders headers,@Context UriInfo uriInfo, WebserviceRequest request)
					throws ObjectNotFoundException, BusinessException,
					EncryptionException {
				DesignationHistory desHistory = (DesignationHistory) JsonUtil.getObject(request.getPayload(),
						DesignationHistory.class);
				Employee output = (Employee) EmployeeHandler.getInstance().updateDesigantionDetailsAOP(desHistory);

				return JsonUtil.getJsonBasedOnDescriptor(output, Employee.class);
			}	
			 /*@POST
			 @RestService(input = String.class, output = String.class)
			 @ServiceStatus(value = "complete")
			 @Consumes(MediaType.APPLICATION_JSON)
			 @Produces(MediaType.APPLICATION_JSON)
			 @Path("/getEmployeesPaginated")
			
			 public String getEmployeesPaginated(@Context HttpHeaders headers,
			   @Context UriInfo uriInfo, WebserviceRequest request)
			   throws ObjectNotFoundException, BusinessException,
			   EncryptionException {
				 NotificationPaginationInput employee = (NotificationPaginationInput) JsonUtil.getObject(
			    request.getPayload(), NotificationPaginationInput.class);
			  PaginationOutput<NotificationPaginationInput> employees = EmployeeHandler.getInstance().getEmployeesPaginated(
			    employee);
			  return JsonUtil.getJsonBasedOnDescriptor(employees,
					  NotificationPaginationInput.class);
			  // return outputString;
			 }*/
			
			
			// Gets
			 @POST
			 @RestService(input = String.class, output = String.class)
			 @ServiceStatus(value = "complete")
			 @Consumes(MediaType.APPLICATION_JSON)
			 @Produces(MediaType.APPLICATION_JSON)
			 @Path("/getEmployeesListPaginated")
			 @UnSecure
			 public String getEmployeesPaginated(@Context HttpHeaders headers,
			   @Context UriInfo uriInfo, WebserviceRequest request)
			   throws ObjectNotFoundException, BusinessException,
			   EncryptionException {
			  EmployeeListPaginationInput employee = (EmployeeListPaginationInput) JsonUtil.getObject(
			    request.getPayload(), EmployeeListPaginationInput.class);
			  PaginationOutput<Employee> employees = EmployeeHandler.getInstance().getEmployeesListPaginated(employee);
			  return JsonUtil.getJsonBasedOnDescriptor(employees,
			     EmployeeListPaginationOutputDescriptor.class);
			 }
			 
			 @POST
			 @RestService(input = String.class, output = String.class)
			 @ServiceStatus(value = "complete")
			 @Consumes(MediaType.APPLICATION_JSON)
			 @Produces(MediaType.APPLICATION_JSON)
			 @Path("/getSearchedEmployeesListPaginated")
			 @UnSecure
			 public String getSearchedEmployeesPaginated(@Context HttpHeaders headers,
			   @Context UriInfo uriInfo, WebserviceRequest request)
			   throws ObjectNotFoundException, BusinessException,
			   EncryptionException {
				 EmployeeSearchInputDescriptor employee = (EmployeeSearchInputDescriptor) JsonUtil.getObject(
			    request.getPayload(), EmployeeSearchInputDescriptor.class);
			  PaginationOutput<Employee> employees = EmployeeHandler.getInstance().getSearchedEmployeesListPaginated(employee);
			  return JsonUtil.getJsonBasedOnDescriptor(employees,
			     EmployeeListPaginationOutputDescriptor.class);
			 }
			 @POST
			 @RestService(input = String.class, output = String.class)
			 @ServiceStatus(value = "complete")
			 @Consumes(MediaType.APPLICATION_JSON)
			 @Produces(MediaType.APPLICATION_JSON)
			 @Path("/getFilterEmployeesListPaginated")
			 @UnSecure
			 public String getFilterEmployeesPaginated(@Context HttpHeaders headers,
			   @Context UriInfo uriInfo, WebserviceRequest request)
			   throws ObjectNotFoundException, BusinessException,
			   EncryptionException {
				 FilterEmployee employee = (FilterEmployee) JsonUtil.getObject(request.getPayload(), FilterEmployee.class);
			  PaginationOutput<Employee> employees = EmployeeHandler.getInstance().getFilterEmployeesPaginated(employee);
			  return JsonUtil.getJsonBasedOnDescriptor(employees,
			     EmployeeListPaginationOutputDescriptor.class);
			 }
}
