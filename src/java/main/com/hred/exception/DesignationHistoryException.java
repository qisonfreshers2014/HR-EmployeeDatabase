package com.hred.exception;
import java.util.List;

public class DesignationHistoryException extends BusinessException {

		private static final long serialVersionUID = 1L;
		public DesignationHistoryException(){
			
		}
		public DesignationHistoryException(int code){
			super(code);
		}
		
		public DesignationHistoryException(int code,String message)
		{
			super(code, message);
		}
		public DesignationHistoryException(int code,String message,List<Object> args){  //
			super(code, message, args);
					
		}
}



