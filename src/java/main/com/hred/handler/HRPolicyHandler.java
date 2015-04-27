package com.hred.handler;

import com.hred.model.HRPolicy;
import com.hred.persistence.dao.DAOFactory;

public class HRPolicyHandler extends AbstractHandler{


	private static HRPolicyHandler INSTANCE = null;

	private HRPolicyHandler() {
		
	}

	public static HRPolicyHandler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new HRPolicyHandler();
		return INSTANCE;
	}


	public HRPolicy save(HRPolicy hrpolicy) {
		HRPolicy hrpolicy_saved = (HRPolicy) DAOFactory.getInstance().getHRPolicyDAO()
				.saveObject(hrpolicy);
		return hrpolicy_saved;
	}

}
