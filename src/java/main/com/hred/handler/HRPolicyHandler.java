package com.hred.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.HRPolicyException;
import com.hred.model.File;
import com.hred.model.HRPolicy;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.FileDAO;
import com.hred.persistence.dao.HRPolicyDAO;
import com.hred.service.descriptors.outputDescriptors.VeiwHRPolicies;


/**
 * @author Bhargavi Uppoju
 *
 */

public class HRPolicyHandler extends AbstractHandler {

	public static final String POLICY_NAME_PATTERN = "^[A-Za-z0-9\\s]*$";
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
		validateFields(policyName, fileId);
		validateFields(policyName, fileId);

		HRPolicy hrpolicy_saved = (HRPolicy) DAOFactory.getInstance()
				.getHRPolicyDAO().saveObject(hrpolicy);
		return hrpolicy_saved;
	}

	public void validateFields(String policyName, long fileID) throws HRPolicyException
	{

		if (policyName == null || policyName.isEmpty()
				|| policyName.trim().isEmpty()) {
			throw new HRPolicyException(ExceptionCodes.HRPolicy_DOESNOT_EXIST,
					ExceptionMessages.HRPolicy_DOESNOT_EXIST);
		}
		boolean isNameValid = Pattern.compile(POLICY_NAME_PATTERN)
				.matcher(policyName).matches();
		
		if (!isNameValid) {
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
					eachpolicyveiw.setFileID(eachfile.getId());
					eachpolicyveiw.setPolicyName(eachfile.getName());
					veiwHRPolicieslist.add(eachpolicyveiw);
				}
			}

		}

		return veiwHRPolicieslist;

	}

}