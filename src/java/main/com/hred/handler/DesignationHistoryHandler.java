package com.hred.handler;


import java.util.List;

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

/*	public DesignationHistory getDesignationById(String id)
			throws DesignationHistoryException {
		DesignationHistory designationhistory = null;
		DesignationHistoryDAO designationhistoryDAOImpl = (DesignationHistoryDAO) DAOFactory.getInstance()
				.getDesignationHistoryDAO();
		designationhistory = designationhistoryDAOImpl.getDesignationById(id);

		return designationhistory;
	}
*/

	public DesignationHistory save(DesignationHistory designationhistory) {
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
	 
}
