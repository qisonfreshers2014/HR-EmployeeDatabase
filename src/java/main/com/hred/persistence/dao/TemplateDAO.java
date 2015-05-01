package com.hred.persistence.dao;

import java.util.List;

import com.hred.exception.TemplateException;
import com.hred.exception.UserException;
import com.hred.model.Template;

public interface TemplateDAO extends BaseDAO{
	
		List<Template> viewTemplate(Template template);

		Template getContentForMail(Template template);

}
