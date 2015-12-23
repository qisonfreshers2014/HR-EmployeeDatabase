package com.hred.persistence.dao;

import java.util.List;

import com.hred.exception.TemplateException;
import com.hred.model.Template;
import com.hred.pagination.PaginationInput;
import com.hred.pagination.Paginator;
import com.hred.service.descriptors.output.DisplayNotificationHome;

public interface TemplateDAO extends BaseDAO{
	public List<Template> viewTemplate(long id) throws TemplateException;
	public List<Template> getTemplates();
	public Template getContentForMail(DisplayNotificationHome template);
	public Paginator<Template> getAllHandsSchedule(PaginationInput alltemplates);
}

