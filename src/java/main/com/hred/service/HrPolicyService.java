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
import com.hred.handler.HrPolicyHandler;
import com.hred.model.HRPolicy;
import com.hred.service.annotations.RestService;
import com.hred.service.annotations.ServiceStatus;
import com.hred.service.annotations.UnSecure;
import com.hred.service.common.WebserviceRequest;

/**
 * @author saisudha
 *
 */
@Path("/v1/policy/")

public class HrPolicyService extends BaseService {
	
	@POST
	@RestService(input = String.class, output = HRPolicy.class)
	@ServiceStatus(value = "complete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getPolicy")
	@UnSecure
	public String getPolicy(@Context HttpHeaders headers, @Context UriInfo uriInfo,
			WebserviceRequest request) throws ObjectNotFoundException,
			BusinessException, EncryptionException {		
		HRPolicy policy = (HRPolicy) JsonUtil.getObject(request.getPayload(), HRPolicy.class);
		List<HRPolicy> policies = HrPolicyHandler.getInstance().getPolicy();
		System.out.println("Count : "+ policies.size());
		return JsonUtil.getJsonForListBasedOnDescriptor(policies, HRPolicy.class, HRPolicy.class);

	}
	

}
