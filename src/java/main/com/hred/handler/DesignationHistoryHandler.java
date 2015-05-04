package com.hred.handler;


import java.sql.Timestamp;
import java.util.List;

import com.hred.exception.DesignationHistoryException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.HolidaysException;
import com.hred.model.DesignationHistory;
import com.hred.model.DesignationType;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.DesignationHistoryDAO;
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

	
	public List<DesignationHistory> getDesignationDetails(DesignationHistory designation) 
	{

		List<DesignationHistory> designations = null;
		DesignationHistoryDAO designationHistorDAOImpl = (DesignationHistoryDAO)DAOFactory.getInstance().getDesignationHistoryDAO();
		designations = (List<DesignationHistory>) designationHistorDAOImpl.getDesignationDetails(designation);
		return designations;
	}
	public List<DesignationHistory> getAllDesignationDetails() 
	{

		List<DesignationHistory> designations = null;
		DesignationHistoryDAO designationHistorDAOImpl = (DesignationHistoryDAO)DAOFactory.getInstance().getDesignationHistoryDAO();
		designations = (List<DesignationHistory>) designationHistorDAOImpl.getAllDesignationDetails();
		return designations;
	}
	public DesignationHistory save(DesignationHistory designationhistory) throws DesignationHistoryException
	{
		
		List<DesignationHistory> data = getAllDesignationDetails();
		int eid=designationhistory.getEmpId();
		Timestamp appraisalDate = designationhistory.getAppraisalDate();
		int designationId = designationhistory.getDesignationId();
		double salary=designationhistory.getSalary();
		double variablePay=designationhistory.getVariablePay();
		validateDuplicate(data,eid,designationId,salary,variablePay);
		validateAppraisalDate(appraisalDate);
		validateDesignationId(designationId);
		validateSalary(salary);
		validateVariablePay(variablePay);
		DesignationHistory designationhistory_saved = (DesignationHistory) DAOFactory.getInstance().getDesignationHistoryDAO()
				.saveObject(designationhistory);
		return designationhistory_saved;
	}

	public List<DesignationType> getDesignationName(DesignationType designation) {
		List<DesignationType> designations = null;
		DesignationHistoryDAO designationHistorDAOImpl = (DesignationHistoryDAO)DAOFactory.getInstance().getDesignationHistoryDAO();
		designations = (List<DesignationType>) designationHistorDAOImpl.getDesignationName(designation);
		return designations;
		}
	
	private void validateDuplicate(List<DesignationHistory> data,int eid, int designationId,double salary,double variablePay) throws DesignationHistoryException
	{

		   for (int i = 0; i < data.size(); i++)
		   {
			   	int eid1=data.get(i).getEmpId();
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
	
	public void validateSalary( double salary) throws DesignationHistoryException
	{

		if (salary == 0) {
			throw new DesignationHistoryException(ExceptionCodes.DESIGNATION_DOESNOT_EXIST,
					ExceptionMessages.DESIGNATION_DOESNOT_EXIST);
		}

	}

		public void validateVariablePay( double variablePay) throws DesignationHistoryException
		{

		if (variablePay == 0) 
		{
			throw new DesignationHistoryException(ExceptionCodes.DESIGNATION_DOESNOT_EXIST,
					ExceptionMessages.DESIGNATION_DOESNOT_EXIST);
		}
	}
	 
}