package com.hred.exception;

import java.util.ArrayList;

public class EmployeeException extends BusinessException {
	
	public EmployeeException() {
		
	}
	
	public EmployeeException(int code) {
		this.setCode(code);
	}

	public EmployeeException(int code, String message) {
		this.setCode(code);
		this.setMessage(message);
		this.setArguments(new ArrayList());
	}

}
