package com.hred.handler;

import java.util.List;

import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.ObjectNotFoundException;
import com.hred.exception.TemplateException;
import com.hred.handler.AbstractHandler;
import com.hred.model.ObjectTypes;
import com.hred.model.Objects;
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

	 private void validationFunc(String templatename, String subject, String templateContent)  throws TemplateException{
		    if (templatename == null || templatename.isEmpty()
		        || templatename.trim().isEmpty()) {
		       throw new TemplateException(ExceptionCodes.EVERY_FIELD_IS_MANDATORY,
		         ExceptionMessages.EVERY_FIELD_IS_MANDATORY);
		      }
		    if (subject == null || subject.isEmpty()
		        || subject.trim().isEmpty()) {
		       throw new TemplateException(ExceptionCodes.EVERY_FIELD_IS_MANDATORY,
		         ExceptionMessages.EVERY_FIELD_IS_MANDATORY);
		      }
		    if (templateContent == null || templateContent.isEmpty()
		        || templateContent.trim().isEmpty()) {
		       throw new TemplateException(ExceptionCodes.EVERY_FIELD_IS_MANDATORY,
		         ExceptionMessages.EVERY_FIELD_IS_MANDATORY);
		      }
		   /* List<Template> data=viewTemplate();
		    for(int i=0;i<data.size();i++){
		    
		     
		     
		     
		    }
		    */
		 }
	public Template save(Template template){
		Template tempSaved=(Template) DAOFactory.getInstance().getTemplateDAO().saveObject(template);
		return tempSaved;
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
	

	public List<Template> viewTemplate(Template template) throws TemplateException {
		List<Template> templates = null;
		TemplateDAO tempDAOImpl = (TemplateDAO) DAOFactory.getInstance().getTemplateDAO();
		templates = (List<Template>) tempDAOImpl.viewTemplate(template);
		return templates;
		
	}

	
		
	
	}




