package com.hred.persistence.dao;

import java.util.List;

import com.hred.exception.TemplateException;
import com.hred.model.Template;
import com.hred.service.descriptors.output.DisplayNotificationHome;

public interface TemplateDAO extends BaseDAO{


	List<Template> viewTemplate(Template template) throws TemplateException;

	List<Template> getTemplates();

	 Template getContentForMail(DisplayNotificationHome template);

	


}

