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
import com.hred.exception.TemplateException;
import com.hred.handler.DesignationTypeHandler;
import com.hred.handler.EmployeeHandler;
import com.hred.handler.TemplateHandler;
import com.hred.model.DesignationType;
import com.hred.model.Employee;
import com.hred.model.Template;
import com.hred.service.annotations.RestService;
import com.hred.service.annotations.ServiceStatus;
import com.hred.service.common.WebserviceRequest;
@Path("/v1/designationtype/")
public class DesignationTypeService extends BaseService  {
	 @POST
	 @RestService(input = String.class, output = String.class)
	 @ServiceStatus(value = "complete")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 @Path("/save")

	 public String save(@Context HttpHeaders headers, @Context UriInfo uriInfo,
	   WebserviceRequest request) throws ObjectNotFoundException,
	   BusinessException, EncryptionException {
		 DesignationType designationType = (DesignationType) JsonUtil.getObject(request.getPayload(),
				 DesignationType.class);

		 DesignationType output = (DesignationType) DesignationTypeHandler.getInstance().saveAOP(
			  designationType);

	  return JsonUtil.getJsonBasedOnDescriptor(output, DesignationType.class);
	 }
	 
	 @POST
		@RestService(input = String.class, output = String.class)
		@ServiceStatus(value = "complete")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/getDesignationTypes")
		
		public String gatDesignationTypes(@Context HttpHeaders headers,
				@Context UriInfo uriInfo, WebserviceRequest request)
				throws ObjectNotFoundException, BusinessException,
				EncryptionException, TemplateException {

		 DesignationType template = (DesignationType) JsonUtil.getObject(request.getPayload(),
				 DesignationType.class);
			List<String> designationTypes = (List<String>) DesignationTypeHandler
					.getInstance().getDesignationTypesAOP();
		
			return JsonUtil.getJsonBasedOnDescriptor(designationTypes, DesignationType.class);
		}
	 
	 	/*@POST
		@RestService(input = String.class, output = String.class)
		@ServiceStatus(value = "complete")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/deleteDesignationType")
		
		public String deleteDesignationType(@Context HttpHeaders headers,
				@Context UriInfo uriInfo, WebserviceRequest request)
				throws ObjectNotFoundException, BusinessException,
				EncryptionException {
	 		System.out.println("Hai");
		 DesignationType designation = (DesignationType) JsonUtil.getObject(request.getPayload(), DesignationType.class);

		 DesignationType output = (DesignationType) DesignationTypeHandler.getInstance()
					.deleteDesignationType(designation);

			return JsonUtil.getJsonBasedOnDescriptor(output, DesignationType.class);
		}*/
	 
	 
}
