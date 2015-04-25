package com.hred.exception;

import java.util.List;

public class TemplateException extends BusinessException {
	private static final long serialVersionUID = 1L;
	public TemplateException(){
		
	}
	public TemplateException(int code){
		super(code);
	}
	
	public TemplateException(int code,String message){
		super(code, message);
	}
	public TemplateException(int code,String message,List<Object> args){  //
		super(code, message, args);
				
	}
}
