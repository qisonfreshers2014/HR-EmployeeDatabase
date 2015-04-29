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
import com.hred.handler.HRPolicyHandler;
import com.hred.handler.user.AuthenticationHandlerFactory;
import com.hred.model.HRPolicy;
import com.hred.model.user.AuthenticationInput;
import com.hred.model.user.AuthenticationOutput;
import com.hred.service.annotations.RestService;
import com.hred.service.annotations.ServiceStatus;
import com.hred.service.annotations.UnSecure;
import com.hred.service.common.WebserviceRequest;
import com.hred.service.descriptors.output.VeiwHRPolicies;
import com.hred.service.descriptors.output.VeiwHRPolicyDiscriptor;

/**
 * @author Bhargavi Uppoju
 *
 */

@Path("/v1/hr_policy/")

public class HRPolicyService extends BaseService{



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
		@RestService(input = HRPolicy.class, output = HRPolicy.class)
		@ServiceStatus(value = "complete")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/save")
		@UnSecure
		public String save(@Context HttpHeaders headers, @Context UriInfo uriInfo,
				WebserviceRequest request) throws ObjectNotFoundException,
				BusinessException, EncryptionException {

			HRPolicy hrpolicy = (HRPolicy) JsonUtil.getObject(request.getPayload(),
					HRPolicy.class);

			HRPolicy output = (HRPolicy) HRPolicyHandler.getInstance().save(hrpolicy);

			return JsonUtil.getJsonBasedOnDescriptor(output, HRPolicy.class);
		}
		
//view
		@POST
		@RestService(input = String.class, output = VeiwHRPolicies.class)
		@ServiceStatus(value = "complete")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@Path("/getPolicy")
		@UnSecure
		public String getPolicy(@Context HttpHeaders headers, @Context UriInfo uriInfo,
				WebserviceRequest request) throws ObjectNotFoundException,
				BusinessException, EncryptionException {		
			HRPolicy policy = (HRPolicy) JsonUtil.getObject(request.getPayload(), HRPolicy.class);
			List<VeiwHRPolicies> policies = HRPolicyHandler.getInstance().getPolicy();
			System.out.println("Count : "+ policies.size());
			return JsonUtil.getJsonForListBasedOnDescriptor(policies, VeiwHRPolicyDiscriptor.class, VeiwHRPolicies.class);

		}

	}
