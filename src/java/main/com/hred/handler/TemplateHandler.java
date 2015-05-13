package com.hred.handler;

import java.util.ArrayList;
import java.util.List;

import com.hred.common.Constants;
import com.hred.exception.BusinessException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.ObjectNotFoundException;
import com.hred.exception.TemplateException;
import com.hred.handler.AbstractHandler;
import com.hred.handler.annotations.AuthorizeEntity;
import com.hred.model.DesignationType;
import com.hred.model.Employee;
import com.hred.model.File;
import com.hred.model.ObjectTypes;
import com.hred.model.Objects;
import com.hred.model.Template;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.DesignationTypeDAO;
import com.hred.persistence.dao.EmployeeDAO;
import com.hred.persistence.dao.FileDAO;
import com.hred.persistence.dao.TemplateDAO;
import com.hred.service.descriptors.output.DisplayNotificationHome;

public class TemplateHandler extends AbstractHandler {

	private static TemplateHandler INSTANCE = null;

	private TemplateHandler() {
	}

	/**
	 * TemplateDAO
	 * 
	 * @return instance of UserHandler
	 */
	public static TemplateHandler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new TemplateHandler();
		return INSTANCE;
	}

	@AuthorizeEntity(roles={Constants.HR})
	public Template saveAOP(Template template) throws TemplateException {
		 String name=template.getName();
		  String subject=template.getSubject();
		  String content=template.getContent();
		 
			validationFunc(name,subject,content,template);

		Template tempSaved = (Template) DAOFactory.getInstance()
				.getTemplateDAO().saveObject(template);
		return tempSaved;
	}
	private void validationFunc(String name, String subject, String content,Template template)  throws TemplateException{
		  if (name == null || name.isEmpty()
				    || name.trim().isEmpty()) {
				   throw new TemplateException(ExceptionCodes.EVERY_FIELD_IS_MANDATORY,
				     ExceptionMessages.EVERY_FIELD_IS_MANDATORY);
				  }
		  if (subject == null || subject.isEmpty()
				    || subject.trim().isEmpty()) {
				   throw new TemplateException(ExceptionCodes.EVERY_FIELD_IS_MANDATORY,
				     ExceptionMessages.EVERY_FIELD_IS_MANDATORY);
				  }
		  if (content == null || content.isEmpty()
				    || content.trim().isEmpty()) {
				   throw new TemplateException(ExceptionCodes.EVERY_FIELD_IS_MANDATORY,
				     ExceptionMessages.EVERY_FIELD_IS_MANDATORY);
				  }
		  List<Template> data=getTemplates();
		  for(int i=0;i<data.size();i++){
			  
			  String dbname=data.get(i).getName();
			  
			 if((dbname.equals(name))){
				 
				  throw new TemplateException(ExceptionCodes.TEMPLATE_ALREADY_EXIST,
				             ExceptionMessages.TEMPLATE_ALREADY_EXIST);
				        
			  }
			  
			  
		  }
		  
	}


 

public List<Template> getTemplates() {
		List<Template> templates = null;
		TemplateDAO temDAOImpl = (TemplateDAO) DAOFactory.getInstance().getTemplateDAO();
		templates = (List<Template>) temDAOImpl.getTemplates();
		return templates;
	}


@AuthorizeEntity(roles={Constants.HR})
	public Template updateAOP(Template template) throws ObjectNotFoundException {

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
	
	@AuthorizeEntity(roles={Constants.HR})
	public Template getContentForMailAOP(DisplayNotificationHome gettemplate) {
		Template receivedtemplatesfromdb = new Template();
		Template templatescontenttoDisplay = new Template();
		String finalContent = "";
		Employee sendNotification=new Employee();
		File requiredImg=new File();
		EmployeeDAO employeeDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
		List<Employee> employeeDetails =new ArrayList<Employee>();
		employeeDetails=employeeDAOImpl.getEmployees();
		DesignationType getDesignationName=new DesignationType();
		DesignationTypeDAO designationTypeDAOImpl = (DesignationTypeDAO) DAOFactory.getInstance()
				.getDesignationTypeDAO();
		
		for(Employee sendNotificationtoEmp:employeeDetails)
		{
			
			if(sendNotificationtoEmp.getEmail().equals(gettemplate.getEmployeeEmail()))
			{
				sendNotification.setEmployeeName(sendNotificationtoEmp.getEmployeeName());
				sendNotification.setSkype(sendNotificationtoEmp.getSkype());
				sendNotification.setHighestQualification(sendNotificationtoEmp.getHighestQualification());
				sendNotification.setCurrentDesignation(sendNotificationtoEmp.getCurrentDesignation());
				sendNotification.setEmail(sendNotificationtoEmp.getEmail());
				sendNotification.setSkype(sendNotificationtoEmp.getSkype());
				sendNotification.setFileId(sendNotificationtoEmp.getFileId());
				
				break;
			}
			
		}
		FileHandler filehandler=FileHandler.getInstance();
		if(sendNotification.getFileId()!=0)
		{
		try {
			 requiredImg=filehandler.getFile(sendNotification.getFileId());
		
		} catch (BusinessException e) {
			
		}	
		}
		getDesignationName=designationTypeDAOImpl.getDesignationByID(sendNotification);
		String currentDesignation=getDesignationName.getName();
		if(gettemplate.getEvent().equalsIgnoreCase("WelCome"))
		{
			
			if(sendNotification.getFileId()!=0)
			{
				 finalContent +="<img src='"+requiredImg.getFilePath()+"' alt='"+sendNotification.getEmployeeName()+"' width='150' height='150'><br/>";
				   
			}
			 System.out.println(requiredImg.getFilePath());
	 finalContent +="Dear Qisonians,<br/> We take Immense pleasure in welcoming "+ sendNotification.getEmployeeName()+" who has Joined QISON TEAM <br/> ";
	finalContent +="He is working as "+currentDesignation+"<br/>";
			 
 finalContent += sendNotification.getEmployeeName()+" as he likes to be called, he has pursued "+ sendNotification.getHighestQualification()+".<br/> He takes keen interest in "+sendNotification.getSkype()+"<br/>";
			 
 finalContent += "He can be reached on "+sendNotification.getEmail()+"<br/>";
			 
 finalContent += "His Skype ID "+sendNotification.getSkype()+"<br/>";
			 
 finalContent += "Please join us in welcoming "+sendNotification.getEmployeeName()+" to QISON family and Wish him a Long and Successful career !!!<br/>";
			                                                                                                  
			 
 finalContent += "Best Regards,<br/>HR Team.";
 templatescontenttoDisplay.setContent(finalContent);
 
		}
		else
		{
		TemplateDAO tempDAOImpl = (TemplateDAO) DAOFactory.getInstance().getTemplateDAO();
		FileDAO fileDAOimpl = (FileDAO) DAOFactory.getInstance().getFileDAO();
		receivedtemplatesfromdb = tempDAOImpl.getContentForMail(gettemplate);
		 finalContent="";
		
		 finalContent ="Hi "+sendNotification.getEmployeeName()+"<br/>"+receivedtemplatesfromdb.getContent();
		System.out.println(sendNotification.getEmployeeName());
		System.out.println(finalContent);
		
		templatescontenttoDisplay.setContent(finalContent);
		System.out.println(templatescontenttoDisplay.getContent());
		}
	return templatescontenttoDisplay;

	}



}
