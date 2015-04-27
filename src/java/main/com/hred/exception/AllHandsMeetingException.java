package com.hred.exception;

import java.util.List;

public class AllHandsMeetingException extends BusinessException{
	private static final long serialVersionUID = 1L;
	public AllHandsMeetingException(){
		
	}
	public AllHandsMeetingException(int code){
		super(code);
	}
	
	public AllHandsMeetingException(int code,String message){
		super(code, message);
	}
	public AllHandsMeetingException(int code,String message,List<Object> args){  //
		super(code, message, args);
				
	}
}
