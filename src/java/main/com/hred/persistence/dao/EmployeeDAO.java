package com.hred.persistence.dao;

import com.hred.exception.UserException;
import com.hred.model.Employee;

public interface EmployeeDAO extends BaseDAO {

	public Employee getUserByEmail(String email) throws UserException;
}
