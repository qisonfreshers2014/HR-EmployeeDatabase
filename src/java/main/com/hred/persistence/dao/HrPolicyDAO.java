/**
 * 
 */
package com.hred.persistence.dao;

import java.util.List;

import com.hred.model.HRPolicy;

/**
 * @author saisudha
 *
 */
public interface HrPolicyDAO extends BaseDAO{

	 public List<HRPolicy> getPolicy();

}
