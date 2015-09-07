package com.hred.handler;


import java.sql.Timestamp;
import java.util.List;

import com.hred.common.Constants;
import com.hred.exception.DesignationHistoryException;
import com.hred.exception.EmployeeException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.HolidaysException;
import com.hred.exception.ObjectNotFoundException;
import com.hred.handler.annotations.AuthorizeEntity;
import com.hred.model.DesignationHistory;
import com.hred.model.DesignationType;
import com.hred.model.Employee;
import com.hred.model.ObjectTypes;
import com.hred.model.Skills;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.DesignationHistoryDAO;
import com.hred.persistence.dao.EmployeeDAO;
import com.hred.persistence.daoimpl.EmployeeDAOImpl;
/**
 * @author Bhargavi Uppoju
 *
 */
public class DesignationHistoryHandler extends AbstractHandler {
	private static DesignationHistoryHandler INSTANCE = null;

	private DesignationHistoryHandler() {
	}

	public static DesignationHistoryHandler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new DesignationHistoryHandler();
		return INSTANCE;
	}

	@AuthorizeEntity(roles={Constants.HR})
	public List<DesignationHistory> getDesignationDetailsAOP(DesignationHistory designation) 
	{

		List<DesignationHistory> designations = null;
		DesignationHistoryDAO designationHistorDAOImpl = (DesignationHistoryDAO)DAOFactory.getInstance().getDesignationHistoryDAO();
		designations = (List<DesignationHistory>) designationHistorDAOImpl.getDesignationDetails(designation);
		return designations;
	}
	@AuthorizeEntity(roles={Constants.HR})
	public List<DesignationHistory> getAllDesignationDetailsAOP() 
	{

		List<DesignationHistory> designations = null;
		DesignationHistoryDAO designationHistorDAOImpl = (DesignationHistoryDAO)DAOFactory.getInstance().getDesignationHistoryDAO();
		designations = (List<DesignationHistory>) designationHistorDAOImpl.getAllDesignationDetails();
		return designations;
	}
	@AuthorizeEntity(roles={Constants.HR})
	public DesignationHistory saveAOP(DesignationHistory designationhistory) throws DesignationHistoryException
	{
		
		List<DesignationHistory> data = getAllDesignationDetailsAOP();
		String eid=designationhistory.getEmpId();
		Timestamp appraisalDate = designationhistory.getAppraisalDate();
		int designationId = designationhistory.getDesignationId();
		double salary=designationhistory.getSalary();
		double variablePay=designationhistory.getVariablePay();
		validateDuplicate(data,eid,designationId,salary,variablePay);
		validateAppraisalDate(appraisalDate);
		validateDesignationId(designationId);
		DesignationHistory designationhistory_saved = (DesignationHistory) DAOFactory.getInstance().getDesignationHistoryDAO()
				.saveObject(designationhistory);
		return designationhistory_saved;
	}
	@AuthorizeEntity(roles={Constants.HR})
public DesignationHistory updateDesignationDetailsAOP(DesignationHistory designation) throws ObjectNotFoundException, DesignationHistoryException, EmployeeException{
		
	List<DesignationHistory> data = getAllDesignationDetailsAOP();
		String eid=designation.getEmpId();
		Timestamp appraisalDate = designation.getAppraisalDate();
		
		
		int designationId = designation.getDesignationId();
		double salary=designation.getSalary();
		double variablePay=designation.getVariablePay();
		validateDuplicate(data,eid,designationId,salary,variablePay);
		validateAppraisalDate(appraisalDate);
		validateDesignationId(designationId);
		DesignationHistory designationFrmdb=(DesignationHistory)DAOFactory.getInstance().getDesignationHistoryDAO().getObjectById(designation.getId(), ObjectTypes.DESIGNATION_HISTORY);
		
		designationFrmdb.setEmpId(designation.getEmpId());
		designationFrmdb.setSalary(designation.getSalary());
		designationFrmdb.setVariablePay(designation.getVariablePay());
		designationFrmdb.setDesignationId(designation.getDesignationId());
		designationFrmdb.setAppraisalDate(designation.getAppraisalDate());
		DesignationHistory updatedDesignation=(DesignationHistory) DAOFactory.getInstance().getDesignationHistoryDAO().update(designationFrmdb);
		
		EmployeeDAOImpl employeeDaoImpl=(EmployeeDAOImpl) DAOFactory.getInstance().getEmployeeDAO();
		Employee employee=employeeDaoImpl.getEmployeeById(updatedDesignation.getEmpId());
		
		
		if(employee.getCurrentDesignation()==designation.getCurrentdesgId()){
		employee.setCurrentDesignation(updatedDesignation.getDesignationId());
		Employee updateEmployee=(Employee) employeeDaoImpl.update(employee);	
     }
		return updatedDesignation;
	
	}

	//@AuthorizeEntity(roles={Constants.HR})
	public List<DesignationType> getDesignationName(DesignationType designation) {
		List<DesignationType> designations = null;
		DesignationHistoryDAO designationHistorDAOImpl = (DesignationHistoryDAO)DAOFactory.getInstance().getDesignationHistoryDAO();
		designations = (List<DesignationType>) designationHistorDAOImpl.getDesignationName(designation);
		return designations;
		}
	
	private void validateDuplicate(List<DesignationHistory> data,String eid, int designationId,double salary,double variablePay) throws DesignationHistoryException
	{

		   for (int i = 0; i < data.size(); i++)
		   {
			   	String eid1=data.get(i).getEmpId();
				int designationId1 = data.get(i).getDesignationId();
				double salary1=data.get(i).getSalary();
				double variablePay1=data.get(i).getVariablePay();     
		         if((eid1==eid)&&(designationId==designationId1)&&(salary==salary1)&&(variablePay==variablePay1))
		         {
		         throw new DesignationHistoryException(ExceptionCodes.DESIGNATIONDETAILS_ALREADY_EXISTS,
		             ExceptionMessages.DESIGNATIONDETAILS_ALREADY_EXISTS);
		        
		         }    
		   }		  
	}
	public void validateAppraisalDate(Timestamp appraisalDate) throws DesignationHistoryException
	{
		if (appraisalDate == null) 
		{
			throw new DesignationHistoryException(ExceptionCodes.DESIGNATION_DOESNOT_EXIST,
					ExceptionMessages.DESIGNATION_DOESNOT_EXIST);
		}
	}
	
	public void validateDesignationId(int designationId) throws DesignationHistoryException
	{
		if (designationId == 0) {
			throw new DesignationHistoryException(ExceptionCodes.DESIGNATION_DOESNOT_EXIST,
					ExceptionMessages.DESIGNATION_DOESNOT_EXIST);
		}

	}

	@AuthorizeEntity(roles={Constants.HR})
	public DesignationHistory getDesignationByIdAOP(DesignationHistory designation) throws ObjectNotFoundException {
		DesignationHistory designationFrmdb=(DesignationHistory)DAOFactory.getInstance().getDesignationHistoryDAO().getObjectById(designation.getId(), ObjectTypes.DESIGNATION_HISTORY);
		return designationFrmdb;
	}
	@AuthorizeEntity(roles={Constants.HR})
	public DesignationHistory deleteDesignationByIdAOP(DesignationHistory designation) throws ObjectNotFoundException {
		DesignationHistory designationFrmdb=getDesignationByIdAOP(designation);
		designationFrmdb.setDeleted(true);
		DesignationHistory updatedDesignation=(DesignationHistory) DAOFactory.getInstance().getDesignationHistoryDAO().update(designationFrmdb);
		return designationFrmdb;
	}	 
	
	
	
}