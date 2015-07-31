package com.hred.handler;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.hred.common.ConfigReader;
import com.hred.common.Constants;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.HRPolicyException;
import com.hred.handler.annotations.AuthorizeEntity;
import com.hred.model.File;
import com.hred.model.HRPolicy;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.FileDAO;
import com.hred.persistence.dao.HRPolicyDAO;
import com.hred.service.descriptors.output.VeiwHRPolicies;

public class HRPolicyHandler extends AbstractHandler {
	private static HRPolicyHandler INSTANCE = null;

	private HRPolicyHandler() {

	}

	public static HRPolicyHandler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new HRPolicyHandler();
		return INSTANCE;
	}
	@AuthorizeEntity(roles={Constants.HR})
	public HRPolicy saveAOP(HRPolicy hrpolicy) throws HRPolicyException{
		List<VeiwHRPolicies> veiwHRPolicieslist=getPolicy();
		String policyName = hrpolicy.getPolicyName();
		validatePolicyName(policyName);
		validateDuplicate(veiwHRPolicieslist,policyName);
		HRPolicy hrpolicy_saved = (HRPolicy) DAOFactory.getInstance()
				.getHRPolicyDAO().saveObject(hrpolicy);
		return hrpolicy_saved;
	}
	private void validateDuplicate(List<VeiwHRPolicies> data,String policyName) throws HRPolicyException
	{
		   for (int i = 0; i < data.size(); i++)
		   {
			   	String policyName1=data.get(i).getPolicyName();    
		         if((policyName1.equalsIgnoreCase(policyName)))
		         {
		        	 throw new HRPolicyException(ExceptionCodes.POLICYNAME_ALREADY_EXISTS,
		             ExceptionMessages.POLICYNAME_ALREADY_EXISTS);
		        
		         }    
		   }		  
	}
	
	public void validatePolicyName(String policyName) throws HRPolicyException
	{
		if (policyName == null || policyName.isEmpty()
				|| policyName.trim().isEmpty()) {
			throw new HRPolicyException(ExceptionCodes.HRPolicy_DOESNOT_EXIST,
					ExceptionMessages.HRPolicy_DOESNOT_EXIST);
		}
	}

	public List<VeiwHRPolicies> getPolicy() {
		String path=null;
		String stage=null;
		try
		{
		Properties props = ConfigReader.getProperties(Constants.FILE_PATH_VARIABLES);		
		stage=props.getProperty(Constants.STAGE_ENVIRONMENT);
		if(stage.equalsIgnoreCase("local"))
		{
			path=props.getProperty(Constants.LOCAL_PATH);
		}
		else if(stage.equalsIgnoreCase("stage"))
		{
			path=props.getProperty(Constants.STAGE_PATH);
		}
		else
		{
			path=props.getProperty(Constants.PRODUCTION_PATH);
		}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		List<VeiwHRPolicies> veiwHRPolicieslist = new ArrayList<VeiwHRPolicies>();
		HRPolicyDAO HrPolicyDAOImpl = (HRPolicyDAO) DAOFactory.getInstance()
				.getHRPolicyDAO();
		FileDAO fileDAOimpl = (FileDAO) DAOFactory.getInstance().getFileDAO();
		List<HRPolicy> policylist = HrPolicyDAOImpl.getPolicy();
		List<File> filelist = fileDAOimpl.getAllFiles();
		for (HRPolicy eachpolicy : policylist) {
			for (File eachfile : filelist) {
				if (eachpolicy.getFileId() == eachfile.getId()) {
					VeiwHRPolicies eachpolicyveiw = new VeiwHRPolicies();
					String filepath=eachfile.getFilePath();
					String replacedPath=filepath.replace("\\", "/");
					eachpolicyveiw.setUrl(path+replacedPath);
					eachpolicyveiw.setFileID(eachfile.getId());
					eachpolicyveiw.setPolicyName(eachpolicy.getPolicyName());
					veiwHRPolicieslist.add(eachpolicyveiw);
			}
		}
		}
		return veiwHRPolicieslist;

	}

}