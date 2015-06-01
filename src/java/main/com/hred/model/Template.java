package com.hred.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hred.persistence.annotations.Increment;

@Entity
@Table(name="TEMPLATE")
@Increment
public class Template extends AbstractObject{
	     @Column(name = "file_id")
	     private int fileId;
		 @Column(name = "template_name")
		 private String name;
		 @Column(name = "subject")
		 private String subject;
		 @Column(name = "content")
		 private String content;
		 
		 
 public Template(){
			 
		 }
		 
		 
  public Template(long id,String name){
	  
	  super.setId(id);
	  this.name=name;
  }
	     
	 public int getFileId() {
		return fileId;
	}



	public void setFileId(int fileId) {
		this.fileId = fileId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}




	

	public int getObjectType() {
		
		return ObjectTypes.TEMPLATE;
	}
}
