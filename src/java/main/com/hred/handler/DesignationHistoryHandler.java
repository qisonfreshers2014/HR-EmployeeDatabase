package com.hred.handler;


import java.sql.Timestamp;
import java.util.List;

import com.hred.exception.DesignationHistoryException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
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

	public DesignationHistory save(DesignationHistory designationhistory) throws DesignationHistoryException
	{
		Timestamp appraisalDate = designationhistory.getAppraisalDate();
		int designationId = designationhistory.getDesignationId();
		double salary=designationhistory.getSalary();
		double variablePay=designationhistory.getVariablePay();
		validateAppraisalDate(appraisalDate);
		validateDesignationId(designationId);
		validateSalary(salary);
		validateVariablePay(variablePay);
		DesignationHistory designationhistory_saved = (DesignationHistory) DAOFactory.getInstance().getDesignationHistoryDAO()
				.saveObject(designationhistory);
		return designationhistory_saved;
	}

	
	public List<DesignationHistory> getDesignationDetails(DesignationHistory designation) {
		List<DesignationHistory> designations = null;
		DesignationHistoryDAO designationHistorDAOImpl = (DesignationHistoryDAO)DAOFactory.getInstance().getDesignationHistoryDAO();
		designations = (List<DesignationHistory>) designationHistorDAOImpl.getDesignationDetails(designation);
		return designations;
		}
	public List<DesignationType> getDesignationName(DesignationType designation) {
		List<DesignationType> designations = null;
		DesignationHistoryDAO designationHistorDAOImpl = (DesignationHistoryDAO)DAOFactory.getInstance().getDesignationHistoryDAO();
		designations = (List<DesignationType>) designationHistorDAOImpl.getDesignationName(designation);
		return designations;
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