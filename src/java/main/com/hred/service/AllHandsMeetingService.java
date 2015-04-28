
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
import com.hred.handler.AllHandsMeetingHandler;
import com.hred.model.AllHandsMeeting;
import com.hred.service.annotations.RestService;
import com.hred.service.annotations.ServiceStatus;
import com.hred.service.annotations.UnSecure;
import com.hred.service.common.WebserviceRequest;

@Path("/v1/allhandsmeeting")
public class AllHandsMeetingService  extends BaseService {
	
	
	@POST
	@RestService(input = AllHandsMeeting.class, output = AllHandsMeeting.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/save")
	@UnSecure
	public String save(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {		
		AllHandsMeeting allhandsmeeting = (AllHandsMeeting) JsonUtil.getObject(request.getPayload(), AllHandsMeeting.class);
		AllHandsMeeting output=(AllHandsMeeting) AllHandsMeetingHandler.getInstance().save(allhandsmeeting);		
		return JsonUtil.getJsonBasedOnDescriptor(output,AllHandsMeeting.class);
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
		
		AllHandsMeeting allhandsmeeting = (AllHandsMeeting) JsonUtil.getObject(
				request.getPayload(), AllHandsMeeting.class);

		String outputString = "{\"status\": \"SUCCESS\", \"payload\": \"Test Successful\"}";
		return outputString;
	}


	@POST
	@RestService(input = AllHandsMeeting.class, output = AllHandsMeeting.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/editAllHandsMeeting")
	@UnSecure
	public String edit(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {		
		AllHandsMeeting allhandsmeeting = (AllHandsMeeting) JsonUtil.getObject(request.getPayload(), AllHandsMeeting.class);
		AllHandsMeeting output=(AllHandsMeeting) AllHandsMeetingHandler.getInstance().edit(allhandsmeeting);		
		return JsonUtil.getJsonBasedOnDescriptor(output,AllHandsMeeting.class);
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
@RestService(input = String.class, output = AllHandsMeeting.class)
@ServiceStatus(value = "complete")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/getAllHandsMeeting")
@UnSecure
public String getAllHandsMeeting(@Context HttpHeaders headers, @Context UriInfo uriInfo,
		WebserviceRequest request) throws ObjectNotFoundException,
		BusinessException, EncryptionException {		
AllHandsMeeting allhandsmeeting = (AllHandsMeeting) JsonUtil.getObject(
			request.getPayload(),AllHandsMeeting.class);
	List<AllHandsMeeting> allhandsmeetings = AllHandsMeetingHandler.getInstance().getAllHandsMeeting(allhandsmeeting);
	System.out.println("Count : "+ allhandsmeetings.size());
	return JsonUtil.getJsonForListBasedOnDescriptor(allhandsmeetings, AllHandsMeeting.class, AllHandsMeeting.class);

}

}
