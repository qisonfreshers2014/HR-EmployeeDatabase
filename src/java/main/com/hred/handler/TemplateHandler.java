package com.hred.handler;

import java.util.List;

import com.hred.exception.TemplateException;
import com.hred.model.Employee;
import com.hred.model.Template;
import com.hred.model.User;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.EmployeeDAO;
import com.hred.handler.AbstractHandler;
import com.hred.model.Template;
import com.hred.model.User;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.TemplateDAO;

public class TemplateHandler extends AbstractHandler {
	private static TemplateHandler INSTANCE = null;

	private TemplateHandler() {
	}

	/**TemplateDAO
	 * @return instance of UserHandler
	 */
	public static TemplateHandler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new TemplateHandler();
		return INSTANCE;
	}


	public Template save(Template template){
		Template tempSaved=(Template) DAOFactory.getInstance().getTemplateDAO().saveObject(template);
		return tempSaved;
	}

	public List<Template> getTemplateByName(Template template) {
		List<Template> templates = null;
		TemplateDAO temDAOImpl = (TemplateDAO) DAOFactory.getInstance().getTemplateDAO();
		templates = (List<Template>) temDAOImpl.getTemplateByName(template);
		return templates;
	}
	public Template update(Template template) {
		// TODO Auto-generated method stub
		Template tempEdited=(Template) DAOFactory.getInstance().getTemplateDAO().saveObject(template);
		return tempEdited;
	}
	

	public List<Template> viewTemplate(Template template) {
		List<Template> templates = null;
		TemplateDAO tempDAOImpl = (TemplateDAO) DAOFactory.getInstance().getTemplateDAO();
		templates = (List<Template>) tempDAOImpl.viewTemplate(template);
		return templates;
		
	}
	




}
