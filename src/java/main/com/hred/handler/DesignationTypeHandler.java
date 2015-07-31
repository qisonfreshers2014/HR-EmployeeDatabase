package com.hred.handler;
/**
 * *
 *
 * @author Rizwan Khan
 *         
 */
import java.util.List;

import com.hred.common.Constants;
import com.hred.exception.DesignationTypeException;
import com.hred.exception.EmployeeException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.TemplateException;
import com.hred.handler.annotations.AuthorizeEntity;
import com.hred.model.DesignationType;
import com.hred.model.Employee;
import com.hred.model.Template;
import com.hred.persistence.dao.BaseDAO;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.DesignationTypeDAO;
import com.hred.persistence.dao.EmployeeDAO;
import com.hred.persistence.dao.TemplateDAO;
import com.hred.persistence.daoimpl.DesignationTypeDAOImpl;

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
@AuthorizeEntity(roles={Constants.HR})
public DesignationType getDesignationByIDAOP(Employee getDesignationNameByID)
{
	DesignationType getDesignationName=null;
	DesignationTypeDAO designationTypeDAOImpl = (DesignationTypeDAO) DAOFactory.getInstance()
			.getDesignationTypeDAO();
	getDesignationName=designationTypeDAOImpl.getDesignationByID(getDesignationNameByID);	
	return getDesignationName;
	
}


@AuthorizeEntity(roles={Constants.HR})
public DesignationType saveAOP(DesignationType designationType) throws DesignationTypeException {
	   String name=designationType.getName();
	   if (name == null || name.isEmpty()
			    || name.trim().isEmpty()) {
			   throw new DesignationTypeException(ExceptionCodes.PLEASE_ENTER_DESIGNATION_TYPE,
			     ExceptionMessages.PLEASE_ENTER_DESIGNATION_TYPE);
			  }
		DesignationType designationsaved = (DesignationType) DAOFactory.getInstance()
			.getTemplateDAO().saveObject(designationType);
	return designationsaved;
}

@AuthorizeEntity(roles={Constants.HR})
public List<String> getDesignationTypesAOP(){
	
	List<String> designationTypes = null;
	DesignationTypeDAO desgTypeDAOImpl = (DesignationTypeDAO) DAOFactory.getInstance().getDesignationTypeDAO();
	designationTypes = (List<String>) desgTypeDAOImpl.getDesignationTypes();
	return designationTypes;
}

public DesignationType deleteDesignationType(DesignationType designation) throws DesignationTypeException {
	DesignationType designationFromDB = (DesignationType) DAOFactory.getInstance()
				.getDesignationTypeDAO().getDesignationTypeById(designation.getId());
	designationFromDB.setDeleted(true);
		DesignationTypeDAO empDAOImpl = (DesignationTypeDAO) DAOFactory.getInstance()
				.getDesignationTypeDAO();
		designation = (DesignationType)empDAOImpl.update(designationFromDB);
		return designation;
	
}

}
