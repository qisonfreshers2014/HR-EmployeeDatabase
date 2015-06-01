package com.hred.exception;

import java.util.List;

public class DesignationTypeException extends BusinessException{

	private static final long serialVersionUID = 1L;
	public DesignationTypeException(){
		
	}
	public DesignationTypeException(int code){
		super(code);
	}
	
	public DesignationTypeException(int code,String message){
		super(code, message);
	}
	public DesignationTypeException(int code,String message,List<Object> args){  //
		super(code, message, args);
				
	}
}
