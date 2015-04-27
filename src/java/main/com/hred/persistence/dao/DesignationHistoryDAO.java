package com.hred.persistence.dao;

import java.util.List;

import com.hred.model.DesignationHistory;
import com.hred.model.DesignationType;

/**
 * Bhargavi Uppoju
 * 
 */
public interface DesignationHistoryDAO extends BaseDAO{
	
	List<DesignationHistory> getDesignationDetails(DesignationHistory designationhistory);
	List<DesignationType> getDesignationName(DesignationType designationhistory);

}
