package com.hred.handler;

import java.util.List;

import com.hred.exception.ObjectNotFoundException;
import com.hred.handler.AbstractHandler;


import com.hred.model.ObjectTypes;
import com.hred.model.Template;
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
	public Template update(Template template) throws ObjectNotFoundException {
		  // TODO Auto-generated method stub
		  Template templateFromDB = (Template)DAOFactory.getInstance().getTemplateDAO().getObjectById(template.getId(), ObjectTypes.TEMPLATE);
		  templateFromDB.setContent(template.getContent());
		  templateFromDB.setName(template.getName());
		  templateFromDB.setSubject(template.getSubject());
		  Template tempEdited=(Template) DAOFactory.getInstance().getTemplateDAO().update(templateFromDB);
		  return tempEdited;
		 }
	

	public List<Template> viewTemplate(Template template) {
		List<Template> templates = null;
		TemplateDAO tempDAOImpl = (TemplateDAO) DAOFactory.getInstance().getTemplateDAO();
		templates = (List<Template>) tempDAOImpl.viewTemplate(template);
		return templates;
	}
	}


	
		
	

