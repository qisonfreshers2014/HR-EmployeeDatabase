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
import com.hred.exception.SkillsException;
 
import com.hred.handler.SkillsHandler;
import com.hred.handler.user.AuthenticationHandlerFactory;
 
import com.hred.model.Skills;
import com.hred.model.user.AuthenticationInput;
import com.hred.model.user.AuthenticationOutput;
import com.hred.service.annotations.RestService;
import com.hred.service.annotations.ServiceStatus;
import com.hred.service.annotations.UnSecure;
import com.hred.service.common.WebserviceRequest;;

/**
 * @author Jyothi Ambepu
 *
 */
 
 
@Path("/v1/skills/") 
public class SkillsService extends BaseService {
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
	
	public String save(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			WebserviceRequest request) throws ObjectNotFoundException,
			BusinessException, EncryptionException {

		Skills skills = (Skills) JsonUtil.getObject(request.getPayload(),
				Skills.class);

		Skills output = (Skills) SkillsHandler.getInstance().saveAOP(skills);

		return JsonUtil.getJsonBasedOnDescriptor(output, Skills.class);
	}
	@POST
	@RestService(input = Skills.class, output = Skills.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public String update(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException {		
		Skills skills = (Skills) JsonUtil.getObject(request.getPayload(), Skills.class);
		Skills output=(Skills) SkillsHandler.getInstance().update(skills);		
		return JsonUtil.getJsonBasedOnDescriptor(output,Skills.class);
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

		Skills skills = (Skills) JsonUtil.getObject(request.getPayload(),
				Skills.class);

		String outputString = "{\"status\": \"SUCCESS\", \"payload\": \"Test Successful\"}";
		return outputString;
	}
	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getSkillsDetails")
	
	public String getSkillsDetails(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			WebserviceRequest request) throws ObjectNotFoundException,
			BusinessException, EncryptionException, SkillsException {	

		Skills skills = (Skills) JsonUtil.getObject(request.getPayload(),
				Skills.class);
		List<Skills> Skills = SkillsHandler.getInstance().getSkillsDetails();
		//System.out.println("COunt : "+ Skills.size());
		return JsonUtil.getJsonBasedOnDescriptor(Skills, Skills.class);
		//String outputString = "{\"status\": \"SUCCESS\", \"payload\": \"COunt\"}";
		//return outputString;
		 
	}
	
	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/geteditskills")
	
	public String getEditSkills(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			WebserviceRequest request) throws ObjectNotFoundException,
			BusinessException, EncryptionException, SkillsException {	

		Skills skills = (Skills) JsonUtil.getObject(request.getPayload(),
				Skills.class);
	
		List<Skills> Skills = SkillsHandler.getInstance().getEditSkills(skills);
		//System.out.println("COunt : "+ Skills.size());
		return JsonUtil.getJsonBasedOnDescriptor(Skills, Skills.class);
		//String outputString = "{\"status\": \"SUCCESS\", \"payload\": \"COunt\"}";
		//return outputString;
		 
		
	}
	@POST
	 @RestService(input = String.class, output = String.class)
	 @ServiceStatus(value = "complete")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("/getSkillsById")

	 public String getSkillsById(@Context HttpHeaders headers, @Context UriInfo uriInfo,
	   WebserviceRequest request) throws ObjectNotFoundException,
	   BusinessException, EncryptionException, SkillsException { 

	  Skills skills = (Skills) JsonUtil.getObject(request.getPayload(),
	    Skills.class);
	  List<Skills> Skills = SkillsHandler.getInstance().getSkillsById(skills.getEmpId());
	  //System.out.println("COunt : "+ Skills.size());
	  return JsonUtil.getJsonBasedOnDescriptor(Skills, Skills.class);
	  //String outputString = "{\"status\": \"SUCCESS\", \"payload\": \"COunt\"}";
	  //return outputString;
	   
	 }
	
	@POST
	 @RestService(input = String.class, output = String.class)
	 @ServiceStatus(value = "complete")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("/deleteSkillById")

	 public String deleteSkillsById(@Context HttpHeaders headers, @Context UriInfo uriInfo,
	   WebserviceRequest request) throws ObjectNotFoundException,
	   BusinessException, EncryptionException, SkillsException { 

	  Skills skills = (Skills) JsonUtil.getObject(request.getPayload(),
	    Skills.class);
	  Skills Skills = SkillsHandler.getInstance().deleteSkillsById(skills.getId());
	  //System.out.println("COunt : "+ Skills.size());
	  return JsonUtil.getJsonBasedOnDescriptor(Skills, Skills.class);
	  //String outputString = "{\"status\": \"SUCCESS\", \"payload\": \"COunt\"}";
	  //return outputString;
	   
	 }
	 

}

