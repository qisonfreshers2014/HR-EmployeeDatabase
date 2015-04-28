package com.hred.handler;

import java.util.List;

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
	

	public List<Template> viewTemplate(Template template) {
		List<Template> templates = null;
		TemplateDAO tempDAOImpl = (TemplateDAO) DAOFactory.getInstance().getTemplateDAO();
		templates = (List<Template>) tempDAOImpl.viewTemplate(template);
		return templates;
		
	}

	
		
	
	}




