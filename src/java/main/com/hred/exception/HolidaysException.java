/**
 * 
 */
package com.hred.exception;

import java.util.List;

/**
 * @author saisudha
 *
 */
public class HolidaysException extends BusinessException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HolidaysException(){
		
	}
	public HolidaysException(int code){
		super(code);
	}
	
	public HolidaysException(int code,String message){
		super(code, message);
	}
	public HolidaysException(int code,String message,List<Object> args){  //
		super(code, message, args);
				
	}
}
