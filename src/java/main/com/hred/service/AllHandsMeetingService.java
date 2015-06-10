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
import com.hred.pagination.AllhandsmeetingInput;
import com.hred.pagination.PaginationOutput;
import com.hred.service.annotations.RestService;
import com.hred.service.annotations.ServiceStatus;
import com.hred.service.annotations.UnSecure;
import com.hred.service.common.WebserviceRequest;
import com.hred.service.descriptors.output.AllhandsOutputDescriptor;

@Path("/v1/allhandsmeeting")
public class AllHandsMeetingService  extends BaseService {
	
	
	@POST
	@RestService(input = AllHandsMeeting.class, output = AllHandsMeeting.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/save")
	
	public String save(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {		
		AllHandsMeeting allhandsmeeting = (AllHandsMeeting) JsonUtil.getObject(request.getPayload(), AllHandsMeeting.class);
		AllHandsMeeting output=(AllHandsMeeting) AllHandsMeetingHandler.getInstance().saveAOP(allhandsmeeting);		
		return JsonUtil.getJsonBasedOnDescriptor(output,AllHandsMeeting.class);
	}
	
	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/test")
	
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
	
	public String getAllHandsMeetingById(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {		
		AllHandsMeeting allhandsmeeting = (AllHandsMeeting) JsonUtil.getObject(request.getPayload(), AllHandsMeeting.class);
		//AllHandsMeeting output=(AllHandsMeeting) AllHandsMeetingHandler.getInstance().getAllHandsMeetingById(allhandsmeeting);
		List<AllHandsMeeting> allhandsmeetings = AllHandsMeetingHandler.getInstance().getAllHandsMeetingByIdAOP(allhandsmeeting);
		return JsonUtil.getJsonBasedOnDescriptor(allhandsmeetings, AllHandsMeeting.class);
		
	}
	
	@POST
	@RestService(input = AllHandsMeeting.class, output = AllHandsMeeting.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	@UnSecure
	public String update(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {		
		AllHandsMeeting allhandsmeeting = (AllHandsMeeting) JsonUtil.getObject(request.getPayload(), AllHandsMeeting.class);
		AllHandsMeeting output=(AllHandsMeeting) AllHandsMeetingHandler.getInstance().updateAOP(allhandsmeeting);		
		return JsonUtil.getJsonBasedOnDescriptor(output,AllHandsMeeting.class);
	}
	
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
	List<AllHandsMeeting> allhandsmeetings = AllHandsMeetingHandler.getInstance().getAllHandsMeeting();
	
	return JsonUtil.getJsonForListBasedOnDescriptor(allhandsmeetings, AllHandsMeeting.class, AllHandsMeeting.class);

}

@POST
@RestService(input = String.class, output = String.class)
@ServiceStatus(value = "complete")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/getallhandsschedule")
@UnSecure
public String getAllHandsMeetingSchedule(@Context HttpHeaders headers,
  @Context UriInfo uriInfo, WebserviceRequest request)
  throws ObjectNotFoundException, BusinessException,
  EncryptionException {
	AllhandsmeetingInput allhands = (AllhandsmeetingInput) JsonUtil.getObject(
   request.getPayload(), AllhandsmeetingInput.class);
 PaginationOutput<AllHandsMeeting> allhandsSchedule = AllHandsMeetingHandler.getInstance().getAllhandsSchedule(allhands);
 return JsonUtil.getJsonBasedOnDescriptor(allhandsSchedule,
		 AllhandsOutputDescriptor.class);
 
}



}

