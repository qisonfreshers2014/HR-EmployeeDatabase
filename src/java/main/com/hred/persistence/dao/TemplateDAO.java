package com.hred.persistence.dao;

import java.util.List;

import com.hred.model.Template;

public interface TemplateDAO extends BaseDAO{

	
	
	List<Template> getTemplateByName();


		Template getContentForMail(Template template);

	
	List<Template> viewTemplate(Template template);
}

