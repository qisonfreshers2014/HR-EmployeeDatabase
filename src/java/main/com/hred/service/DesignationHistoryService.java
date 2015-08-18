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
import com.hred.exception.DesignationHistoryException;
import com.hred.exception.EncryptionException;
import com.hred.exception.ObjectNotFoundException;
import com.hred.handler.DesignationHistoryHandler;
import com.hred.model.DesignationHistory;
import com.hred.model.DesignationType;
import com.hred.service.annotations.RestService;
import com.hred.service.annotations.ServiceStatus;
import com.hred.service.annotations.UnSecure;
import com.hred.service.common.WebserviceRequest;


/**
 * @author Bhargavi Uppoju
 *
 */

@Path("/v1/designation_history/")

public class DesignationHistoryService extends BaseService{


	
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
		@RestService(input = DesignationHistory.class, output = DesignationHistory.class)
		@ServiceStatus(value = "complete")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/save")
		
		public String save(@Context HttpHeaders headers, @Context UriInfo uriInfo,
				WebserviceRequest request) throws ObjectNotFoundException,
				BusinessException, EncryptionException {

			DesignationHistory designationmaster = (DesignationHistory) JsonUtil.getObject(request.getPayload(),
					DesignationHistory.class);

			DesignationHistory output = (DesignationHistory) DesignationHistoryHandler.getInstance().saveAOP(
					designationmaster);

			return JsonUtil.getJsonBasedOnDescriptor(output, DesignationHistory.class);
		}
		
		@POST
		@RestService(input = DesignationHistory.class, output = DesignationHistory.class)
		@ServiceStatus(value = "complete")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/upadateDesignation")
		
		public String upadateDesignation(@Context HttpHeaders headers, @Context UriInfo uriInfo,
				WebserviceRequest request) throws ObjectNotFoundException,
				BusinessException, EncryptionException {

			DesignationHistory designationmaster = (DesignationHistory) JsonUtil.getObject(request.getPayload(),DesignationHistory.class);

			DesignationHistory output = (DesignationHistory) DesignationHistoryHandler.getInstance().updateDesignationDetailsAOP(designationmaster);

			return JsonUtil.getJsonBasedOnDescriptor(output, DesignationHistory.class);
		}
		
		@POST
		@RestService(input = DesignationHistory.class, output = DesignationHistory.class)
		@ServiceStatus(value = "complete")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/getDesignations")
	
		public String getDesignationDetails(@Context HttpHeaders headers, @Context UriInfo uriInfo,
		WebserviceRequest request) throws ObjectNotFoundException,
		BusinessException, EncryptionException, DesignationHistoryException {
		 
		DesignationHistory designation = (DesignationHistory) JsonUtil.getObject(request.getPayload(),
		DesignationHistory.class);
		 
		List<DesignationHistory> designations = DesignationHistoryHandler.getInstance().getDesignationDetailsAOP(designation);
		System.out.println("Count : "+ designations.size());

		return JsonUtil.getJsonForListBasedOnDescriptor(designations, DesignationHistory.class, DesignationHistory.class);
		 
		}
		
		@POST
		@RestService(input = DesignationHistory.class, output = DesignationHistory.class)
		@ServiceStatus(value = "complete")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/getAllDesignations")

		public String getAllDesignationDetails(@Context HttpHeaders headers, @Context UriInfo uriInfo,
		WebserviceRequest request) throws ObjectNotFoundException,
		BusinessException, EncryptionException, DesignationHistoryException {
		 
		List<DesignationHistory> designations = DesignationHistoryHandler.getInstance().getAllDesignationDetailsAOP();
		System.out.println("Count : "+ designations.size());

		return JsonUtil.getJsonForListBasedOnDescriptor(designations, DesignationHistory.class, DesignationHistory.class);
		 
		}
		
		@POST
		@RestService(input = DesignationType.class, output = DesignationType.class)
		@ServiceStatus(value = "complete")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/getDesignationName")
	
		public String getDesignationName(@Context HttpHeaders headers, @Context UriInfo uriInfo,
		WebserviceRequest request) throws ObjectNotFoundException,
		BusinessException, EncryptionException, DesignationHistoryException {
		 
		DesignationType designation = (DesignationType) JsonUtil.getObject(request.getPayload(),
		DesignationType.class);
		 
		List<DesignationType> designationName = DesignationHistoryHandler.getInstance().getDesignationName(designation);
		System.out.println("Count : "+ designationName.size());

		return JsonUtil.getJsonForListBasedOnDescriptor(designationName, DesignationType.class, DesignationType.class);
}		

		@POST
		@RestService(input = DesignationType.class, output = DesignationType.class)
		@ServiceStatus(value = "complete")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/getDesignationById")
	
		public String getDesignationById(@Context HttpHeaders headers, @Context UriInfo uriInfo,
		WebserviceRequest request) throws ObjectNotFoundException,
		BusinessException, EncryptionException, DesignationHistoryException {
		 
			DesignationHistory designation = (DesignationHistory) JsonUtil.getObject(request.getPayload(),
					DesignationHistory.class);
		 
		DesignationHistory designationName = DesignationHistoryHandler.getInstance().getDesignationByIdAOP(designation);
	

		return JsonUtil.getJsonForListBasedOnDescriptor(designationName, DesignationType.class, DesignationType.class);
}		
		
		@POST
		@RestService(input = DesignationType.class, output = DesignationType.class)
		@ServiceStatus(value = "complete")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/deleteDesignationById")
	
		public String deleteDesignationById(@Context HttpHeaders headers, @Context UriInfo uriInfo,
		WebserviceRequest request) throws ObjectNotFoundException,
		BusinessException, EncryptionException, DesignationHistoryException {
		 
			DesignationHistory designation = (DesignationHistory) JsonUtil.getObject(request.getPayload(),
					DesignationHistory.class);
		 
		DesignationHistory designationName = DesignationHistoryHandler.getInstance().deleteDesignationByIdAOP(designation);
	

		return JsonUtil.getJsonForListBasedOnDescriptor(designationName, DesignationType.class, DesignationType.class);
}		


}
