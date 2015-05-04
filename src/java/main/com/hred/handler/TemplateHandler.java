package com.hred.handler;

import java.util.List;

import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.ObjectNotFoundException;
import com.hred.exception.TemplateException;
import com.hred.model.Employee;
import com.hred.model.ObjectTypes;
import com.hred.model.Template;
import com.hred.persistence.dao.DAOFactory;
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

 public Template save(Template template) throws TemplateException {
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
    List<Template> data=getTemplateByName();
    for(int i=0;i<data.size();i++){
     
     String dbname=data.get(i).getName();
     
    if((dbname.equals(name))){
     
      throw new TemplateException(ExceptionCodes.TEMPLATE_ALREADY_EXIST,
                 ExceptionMessages.TEMPLATE_ALREADY_EXIST);
            
     }
     
     
    }
    
 }


 
 public List<Template> getTemplateByName() {
  List<Template> templates = null;
  TemplateDAO temDAOImpl = (TemplateDAO) DAOFactory.getInstance().getTemplateDAO();
  templates = (List<Template>) temDAOImpl.getTemplateByName();
  return templates;

 }

 

 public List<Template> viewTemplate(Template template) throws TemplateException {
  List<Template> templates = null;
  TemplateDAO tempDAOImpl = (TemplateDAO) DAOFactory.getInstance()
    .getTemplateDAO();
  templates = (List<Template>) tempDAOImpl.viewTemplate(template);
  return templates;

 }

 public Template getContentForMail(DisplayNotificationHome gettemplate) {
	  Template receivedtemplates = new Template();
	  String url = "";
	  Employee sendNotification=new Employee();
	  EmployeeDAO employeeDAOImpl = DAOFactory.getInstance().getEmployeeDAO();
	  List<Employee> employeeDetails=employeeDAOImpl.getEmployees();
	  
	  for(Employee sendNotificationtoEmp:employeeDetails)
	  {
	   if(sendNotificationtoEmp.getEmail().equals(gettemplate.getEmployeeEmail()))
	   {
	    sendNotification.setEmployeeName(sendNotificationtoEmp.getEmployeeName());
	    sendNotification.setSkype(sendNotificationtoEmp.getSkype());
	    sendNotification.setHighestQualification(sendNotificationtoEmp.getHighestQualification());
	    sendNotification.setCurrentDesignation(sendNotificationtoEmp.getCurrentDesignation());
	    sendNotification.setEmail(sendNotificationtoEmp.getEmail());
	    sendNotification.setSkype(sendNotificationtoEmp.getSkill());
	    break;
	   }
	  }
	  if(gettemplate.getEvent().equalsIgnoreCase("WelCome"))
	  {
	 String finalContent="Dear Qisonians,<br/> We take Immense pleasure in welcoming "+ sendNotification.getEmployeeName()+" who has Joined QISON TEAM <br/> ";
	    
	 finalContent += sendNotification.getEmployeeName()+" as he likes to be called, he has pursued "+ sendNotification.getHighestQualification()+".<br/> He takes keen interest in "+sendNotification.getSkill()+"<br/>";
	    
	 finalContent += "He can be reached on "+sendNotification.getEmail()+"<br/>";
	    
	 finalContent += "His Skype ID "+sendNotification.getSkype()+"<br/>";
	    
	 finalContent += "Please join us in welcoming "+sendNotification.getEmployeeName()+" to QISON family and Wish him a Long and Successful career !!!<br/>";
	                                                                                                     
	    
	 finalContent += "Best Regards,<br/>HR Team.";
	 receivedtemplates.setContent(finalContent);
	 
	  }
	  else
	  {
	  TemplateDAO tempDAOImpl = (TemplateDAO) DAOFactory.getInstance().getTemplateDAO();
	  FileDAO fileDAOimpl = (FileDAO) DAOFactory.getInstance().getFileDAO();
	  receivedtemplates = tempDAOImpl.getContentForMail(gettemplate);
	  String finalContent ="Hi "+sendNotification.getEmployeeName()+"<br/>"+receivedtemplates.getContent();
	  
	  
	  /*List<File> filelist = fileDAOimpl.getAllFiles();
	  for (File eachfile : filelist) {
	   if (eachfile.getId() == receivedtemplates.getFileId()) {
	    url = eachfile.getFilePath();

	   }  

	  }
	  finalContent+="<br/><<img src="+url+" width=\"250\" height=\"95\" border=\"0\" alt="+receivedtemplates.getSubject()+"><br>";
	  template.setContent(finalContent);
	  */  
	  receivedtemplates.setContent(finalContent);
	  }
	 
	  
	  
	  

	  return receivedtemplates;

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
   

  

}