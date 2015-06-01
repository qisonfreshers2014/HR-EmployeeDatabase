package com.hred.persistence.dao;

import java.util.List;

import com.hred.exception.DesignationTypeException;
import com.hred.model.DesignationType;
import com.hred.model.Employee;

public interface DesignationTypeDAO extends BaseDAO {
	public DesignationType getDesignationByID(Employee getDesignation);

	public List<String> getDesignationTypes();

	public DesignationType getDesignationTypeById(long id) throws DesignationTypeException;
}
