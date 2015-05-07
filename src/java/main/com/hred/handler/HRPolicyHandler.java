package com.hred.handler;


import java.util.ArrayList;
import java.util.List;

import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.HRPolicyException;
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

	public HRPolicy save(HRPolicy hrpolicy) throws HRPolicyException{
		String policyName = hrpolicy.getPolicyName();
		long fileId = hrpolicy.getFileId();
		validatePolicyName(policyName);
		validateFileId(fileId);
		HRPolicy hrpolicy_saved = (HRPolicy) DAOFactory.getInstance()
				.getHRPolicyDAO().saveObject(hrpolicy);
		return hrpolicy_saved;
	}

	public void validatePolicyName(String policyName) throws HRPolicyException
	{

		if (policyName == null || policyName.isEmpty()
				|| policyName.trim().isEmpty()) {
			throw new HRPolicyException(ExceptionCodes.HRPolicy_DOESNOT_EXIST,
					ExceptionMessages.HRPolicy_DOESNOT_EXIST);
		}


	}
	public void validateFileId(long fileId) throws HRPolicyException
	{

		if (fileId == 0) 
		{
			throw new HRPolicyException(ExceptionCodes.HRPolicy_DOESNOT_EXIST,
					ExceptionMessages.HRPolicy_DOESNOT_EXIST);
		}


	}

	public List<VeiwHRPolicies> getPolicy() {

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
					eachpolicyveiw.setUrl(eachfile.getFilePath());
					eachpolicyveiw.setFileID(eachfile.getId());
					eachpolicyveiw.setPolicyName(eachpolicy.getPolicyName());
					veiwHRPolicieslist.add(eachpolicyveiw);
				
			}

		}
		}
		return veiwHRPolicieslist;

	}



}