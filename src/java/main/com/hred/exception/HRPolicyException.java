package com.hred.exception;

import java.util.List;

public class HRPolicyException extends BusinessException {

	private static final long serialVersionUID = 1L;
	public HRPolicyException(){
		
	}
	public HRPolicyException(int code){
		super(code);
	}
	
	public HRPolicyException(int code,String message)
	{
		super(code, message);
	}
	public HRPolicyException(int code,String message,List<Object> args){  //
		super(code, message, args);
				
	}
}

