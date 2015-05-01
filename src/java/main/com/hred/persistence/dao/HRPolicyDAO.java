package com.hred.persistence.dao;

import java.util.List;

import com.hred.model.HRPolicy;


public interface HRPolicyDAO extends BaseDAO{

	List<HRPolicy> getPolicy();

	
}
