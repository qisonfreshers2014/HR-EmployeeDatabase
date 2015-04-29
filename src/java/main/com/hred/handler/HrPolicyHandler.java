/**
 * 
 */
package com.hred.handler;

import java.util.List;

import com.hred.model.HRPolicy;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.HrPolicyDAO;

/**
 * @author saisudha
 *
 */
public class HrPolicyHandler extends AbstractHandler {
	
	
	private static HrPolicyHandler INSTANCE = null;
	
	private HrPolicyHandler() {
	}
	
	public static HrPolicyHandler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new HrPolicyHandler();
		return INSTANCE;
	}
	
	
	public List<HRPolicy> getPolicy(){
		List<HRPolicy> policylist = null;
		HrPolicyDAO HrPolicyDAOImpl = (HrPolicyDAO) DAOFactory.getInstance().getHrPolicyDAO();
		policylist = (List<HRPolicy>)HrPolicyDAOImpl.getPolicy();
		return policylist;
	
	}

	
}
