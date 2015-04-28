package com.hred.persistence.daoimpl;

import com.hred.persistence.dao.HRPolicyDAO;

public class HRPolicyDAOImpl  extends BaseDAOImpl implements HRPolicyDAO{
	
private static HRPolicyDAO INSTANCE = null;
	
	private HRPolicyDAOImpl(){
		
	}
	public static HRPolicyDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new HRPolicyDAOImpl();
		}
		return INSTANCE;
	}

	
}
