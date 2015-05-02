package com.hred.persistence.dao;

import java.util.List;

import com.hred.model.HRPolicy;


/**
 * @author Bhargavi Uppoju
 *
 */
public interface HRPolicyDAO extends BaseDAO{

	List<HRPolicy> getPolicy();

	
}
