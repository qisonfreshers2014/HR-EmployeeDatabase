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
import com.hred.handler.HolidayHandler;
import com.hred.model.Holiday;
import com.hred.service.annotations.RestService;
import com.hred.service.annotations.ServiceStatus;
import com.hred.service.annotations.UnSecure;
import com.hred.service.common.WebserviceRequest;

/**
 * @author saisudha
 *
 */
@Path("/v1/holiday/")
public class HolidayService extends BaseService {
	
	
	@POST
	@RestService(input = Holiday.class, output = Holiday.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/save")
	@UnSecure
	public String save(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {		
		Holiday holiday = (Holiday) JsonUtil.getObject(request.getPayload(), Holiday.class);
		Holiday output=(Holiday) HolidayHandler.getInstance().save(holiday);		
		return JsonUtil.getJsonBasedOnDescriptor(output,Holiday.class);
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
		
		Holiday holidays = (Holiday) JsonUtil.getObject(
				request.getPayload(), Holiday.class);

		String outputString = "{\"status\": \"SUCCESS\", \"payload\": \"Test Successful\"}";
		return outputString;
	}


	@POST
	@RestService(input = Holiday.class, output = Holiday.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/editHoliday")
	@UnSecure
	public String editHoliday(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {		
		Holiday holiday = (Holiday) JsonUtil.getObject(request.getPayload(), Holiday.class);
		Holiday output=(Holiday) HolidayHandler.getInstance().editHoliday(holiday);		
		return JsonUtil.getJsonBasedOnDescriptor(output,Holiday.class);
	}
	
/*@POST
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
return JsonUtil.getJsonForListBasedOnDescriptor(employees, Employee.class, FilterListOutputDescriptors.class);
 
}*/

@POST
@RestService(input = String.class, output = Holiday.class)
@ServiceStatus(value = "complete")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/getHolidays")
@UnSecure
public String getHolidays(@Context HttpHeaders headers, @Context UriInfo uriInfo,
		WebserviceRequest request) throws ObjectNotFoundException,
		BusinessException, EncryptionException {		
	Holiday holiday = (Holiday) JsonUtil.getObject(
			request.getPayload(), Holiday.class);
	List<Holiday> holidays = HolidayHandler.getInstance().getHolidays(holiday);
	System.out.println("Count : "+ holidays.size());
	return JsonUtil.getJsonForListBasedOnDescriptor(holidays, Holiday.class, Holiday.class);

}

}

