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
import com.hred.handler.AllHandsMeetingHandler;
import com.hred.handler.TemplateHandler;
import com.hred.model.AllHandsMeeting;
import com.hred.model.Template;
import com.hred.pagination.AllhandsmeetingInput;
import com.hred.pagination.PaginationInput;
import com.hred.pagination.PaginationOutput;
import com.hred.service.annotations.RestService;
import com.hred.service.annotations.ServiceStatus;
import com.hred.service.annotations.UnSecure;
import com.hred.service.common.WebserviceRequest;
import com.hred.service.descriptors.output.AllhandsOutputDescriptor;
import com.hred.service.descriptors.output.DisplayNotificationHome;
import com.hred.service.descriptors.output.TemplateOutputDescriptor;

@Path("/v1/template/")
public class TemplateService extends BaseService {



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
 @Path("/save")

 public String save(@Context HttpHeaders headers, @Context UriInfo uriInfo,
   WebserviceRequest request) throws ObjectNotFoundException,
   BusinessException, EncryptionException {
  Template template = (Template) JsonUtil.getObject(request.getPayload(),
    Template.class);

  Template output = (Template) TemplateHandler.getInstance().saveAOP(
    template);

  return JsonUtil.getJsonBasedOnDescriptor(output, Template.class);
 }

	@POST
	@RestService(input = String.class, output = String.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/gatTemplate")
	
	public String getTemplates(@Context HttpHeaders headers,
			@Context UriInfo uriInfo, WebserviceRequest request)
			throws ObjectNotFoundException, BusinessException,
			EncryptionException, TemplateException {

		Template template = (Template) JsonUtil.getObject(request.getPayload(),
				Template.class);
		List<Template> templates = (List<Template>) TemplateHandler
				.getInstance().getTemplatesAOP();
		System.out.println("COunt : " + templates.size());
		// String outputString =
		// "{\"status\": \"SUCCESS\", \"payload\": \"Test Successful\"}";
		return JsonUtil.getJsonBasedOnDescriptor(templates, Template.class);
	}
 @POST
 @RestService(input = String.class, output = String.class)
 @ServiceStatus(value = "complete")
 @Consumes(MediaType.APPLICATION_JSON)
 @Produces(MediaType.APPLICATION_JSON)
 @Path("/viewTemplate")
 
 public String viewTemplate(@Context HttpHeaders headers,
   @Context UriInfo uriInfo, WebserviceRequest request)
   throws ObjectNotFoundException, BusinessException,
   EncryptionException, TemplateException {

  Template template = (Template) JsonUtil.getObject(request.getPayload(),
    Template.class);
  List<Template> templates = TemplateHandler.getInstance().viewTemplateAOP(template.getId());

  // String outputString =
  // "{\"status\": \"SUCCESS\", \"payload\": \"Test Successful\"}";
  return JsonUtil.getJsonBasedOnDescriptor(templates, Template.class);
 }



 @POST
 @RestService(input = String.class, output = String.class)
 @ServiceStatus(value = "complete")
 @Consumes(MediaType.APPLICATION_JSON)
 @Produces(MediaType.APPLICATION_JSON)
 @Path("/getContentForMail")

 public String getContentForMail(@Context HttpHeaders headers,
   @Context UriInfo uriInfo, WebserviceRequest request)
   throws ObjectNotFoundException, BusinessException,
   EncryptionException {

  DisplayNotificationHome template = (DisplayNotificationHome) JsonUtil.getObject(request.getPayload(),
    DisplayNotificationHome.class);

  Template subject = TemplateHandler.getInstance().getContentForMailAOP(template);
  return JsonUtil.getJsonBasedOnDescriptor(subject, Template.class);
 }
 @POST
  @RestService(input = String.class, output = String.class)
  @ServiceStatus(value = "complete")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/updateTemplate")
  
  public String update(@Context HttpHeaders headers,
    @Context UriInfo uriInfo, WebserviceRequest request)
    throws ObjectNotFoundException, BusinessException,
    EncryptionException {

   Template template = (Template) JsonUtil.getObject(
     request.getPayload(), Template.class);

   Template output=(Template) TemplateHandler.getInstance().updateAOP(template);

   return JsonUtil.getJsonBasedOnDescriptor(output,Template.class);
  }
  
 @POST
 @RestService(input = String.class, output = String.class)
 @ServiceStatus(value = "complete")
 @Consumes(MediaType.APPLICATION_JSON)
 @Produces(MediaType.APPLICATION_JSON)
 @Path("/getalltemplates")
 @UnSecure
 public String getAllTemplatesPaginated(@Context HttpHeaders headers,
   @Context UriInfo uriInfo, WebserviceRequest request)
   throws ObjectNotFoundException, BusinessException,
   EncryptionException {
 	PaginationInput alltemplates = (PaginationInput) JsonUtil.getObject(
    request.getPayload(), PaginationInput.class);
  PaginationOutput<Template> templates = TemplateHandler.getInstance().getAllTemplatesPaginated(alltemplates);
  return JsonUtil.getJsonBasedOnDescriptor(templates, TemplateOutputDescriptor.class);
  
 }


  

}

