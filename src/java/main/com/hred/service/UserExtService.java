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
import com.hred.handler.UserExtHandler;
import com.hred.service.annotations.RestService;
import com.hred.service.annotations.ServiceStatus;
import com.hred.service.annotations.UnSecure;
import com.hred.service.common.WebserviceRequest;


/**
 * @author Kavinder
 */
@Path("/v1/demo")
public class UserExtService extends BaseService{
	
	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/testService")
	
	public String testService(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			WebserviceRequest request) {
		return "{\"status\":\"SUCCESS\", \"payload\":\"Hurry its working !!!!\"}";
	}
	
	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/testHandler")
	public String testHandler(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			WebserviceRequest request) {
	String testResult = UserExtHandler.getInstance().getTestResult();
	return "{\"status\":\"SUCCESS\", \"payload\":\"Hurry its working !!!!\"}";
	}
	
	@POST
    @RestService(input = String.class, output = Boolean.class)
    @ServiceStatus(value = "complete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/testWithInput")
    public Boolean testWithInput(@Context HttpHeaders headers, @Context UriInfo uriInfo,
                       WebserviceRequest request) throws Exception {
        String text = (String) JsonUtil.getObject(request.getPayload(),
        		String.class);
        boolean testResult = UserExtHandler.getInstance().testWithInput(text);
        return testResult;
    }

}
