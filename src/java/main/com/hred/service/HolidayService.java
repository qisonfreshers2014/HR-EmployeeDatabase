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
	
	//save service for Holiday
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
		Holiday output=(Holiday) HolidayHandler.getInstance().saveAOP(holiday);		
		return JsonUtil.getJsonBasedOnDescriptor(output,Holiday.class);
	}
	
	//test service
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
		
		Holiday holidays = (Holiday) JsonUtil.getObject(request.getPayload(), Holiday.class);

		String outputString = "{\"status\": \"SUCCESS\", \"payload\": \"Test Successful\"}";
		return outputString;
	}


	//update service method for Holiday
	@POST
	@RestService(input = Holiday.class, output = Holiday.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/updateHoliday")
	@UnSecure
	public String updateHoliday(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {		
		Holiday holiday = (Holiday) JsonUtil.getObject(request.getPayload(), Holiday.class);
		Holiday output=(Holiday) HolidayHandler.getInstance().updateHolidayAOP(holiday);		
		return JsonUtil.getJsonBasedOnDescriptor(output,Holiday.class);
	}
	
	//service for getting Holiday by date
	  @POST
	  @RestService(input = String.class, output = Holiday.class)
	  @ServiceStatus(value = "complete")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  @Path("/holidayByDate")
	  @UnSecure
	  public String getHolidayByDate(
	    @Context HttpHeaders headers, @Context UriInfo uriInfo,
	    WebserviceRequest request) throws ObjectNotFoundException,
	    BusinessException, EncryptionException {
	  Holiday holiday = (Holiday) JsonUtil.getObject(request.getPayload(),Holiday.class);
	  Holiday  holidays = (Holiday) HolidayHandler.getInstance().getHolidayByDateAOP(holiday);
	  return JsonUtil.getJsonBasedOnDescriptor(holidays,Holiday.class);
	  
	  }
	
	
	
	
	 //service for getting Holiday by Id
	 @POST
	 @RestService(input = String.class, output = Holiday.class)
	 @ServiceStatus(value = "complete")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("/getHolidayId")
	 @UnSecure
	 public String getHolidayById(
	   @Context HttpHeaders headers, @Context UriInfo uriInfo,
	   WebserviceRequest request) throws ObjectNotFoundException,
	   BusinessException, EncryptionException {
		Holiday holiday = (Holiday) JsonUtil.getObject(request.getPayload(),Holiday.class);
		List<Holiday>  holidays = (List<Holiday>) HolidayHandler.getInstance().getHolidayById(holiday);
		System.out.println("Count : "+ holidays.size());
	  return JsonUtil.getJsonForListBasedOnDescriptor(holidays, Holiday.class,Holiday.class);
	 
	 }
	
	
	 //service for getting List of Holidays
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
		 Holiday holiday = (Holiday) JsonUtil.getObject(request.getPayload(), Holiday.class);
		 List<Holiday> holidays = HolidayHandler.getInstance().getHolidays();
		 System.out.println("Count : "+ holidays.size());
		 return JsonUtil.getJsonForListBasedOnDescriptor(holidays, Holiday.class, Holiday.class);

	 }

	}

