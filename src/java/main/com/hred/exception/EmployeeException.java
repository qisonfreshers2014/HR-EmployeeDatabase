package com.hred.exception;

import java.util.List;

/**
 * @author saisudha
 *
 */
public class EmployeeException extends BusinessException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EmployeeException(){
		
	}
	public EmployeeException(int code){
		super(code);
	}
	
	public EmployeeException(int code,String message){
		super(code, message);
	}
	public EmployeeException(int code,String message,List<Object> args){  //
		super(code, message, args);
				
	}

}
