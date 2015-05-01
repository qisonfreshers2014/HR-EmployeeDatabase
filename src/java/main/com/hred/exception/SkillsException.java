package com.hred.exception;

import java.util.List;

public class SkillsException extends BusinessException{
 
	 private static final long serialVersionUID = 1L;
	  public SkillsException(){
	   
	  }
	  public SkillsException(int code){
	   super(code);
	  }
	  
	  public SkillsException(int code,String message)
	  {
	   super(code, message);
	  }
	  public SkillsException(int code,String message,List<Object> args){  //
	   super(code, message, args);
	     
	  }
	}

