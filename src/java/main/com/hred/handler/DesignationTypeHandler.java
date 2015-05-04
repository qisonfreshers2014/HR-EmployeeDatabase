package com.hred.handler;
/**
 * *
 *
 * @author Rizwan Khan
 *         
 */
import com.hred.model.DesignationType;
import com.hred.model.Employee;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.DesignationTypeDAO;
import com.hred.persistence.dao.EmployeeDAO;

public class DesignationTypeHandler extends AbstractHandler 
{
private static DesignationTypeHandler INSTANCE = null;

private DesignationTypeHandler() {
 
 
}

public static DesignationTypeHandler getInstance() {
 if (INSTANCE == null)
  INSTANCE = new DesignationTypeHandler();
 return INSTANCE;


}
//This method can be used to map the employee table with the designation type table 
//to get the Designation Name from the Designation id
public DesignationType getDesignationByID(Employee getDesignationNameByID)
{
	DesignationType getDesignationName=null;
	DesignationTypeDAO designationTypeDAOImpl = (DesignationTypeDAO) DAOFactory.getInstance()
			.getDesignationTypeDAO();
	getDesignationName=designationTypeDAOImpl.getDesignationByID(getDesignationNameByID);	
	return getDesignationName;
	
}


}
