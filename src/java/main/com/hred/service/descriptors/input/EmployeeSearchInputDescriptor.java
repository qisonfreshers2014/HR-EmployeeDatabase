/**
 * 
 */
package com.hred.service.descriptors.input;

import com.hred.pagination.PaginationInput;


/**
 * @author saisudha
 *
 */
public class EmployeeSearchInputDescriptor extends PaginationInput{
private String searchKey;
	
	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public EmployeeSearchInputDescriptor(String searchKey) {
		super();
		this.searchKey = searchKey;
	}

	public EmployeeSearchInputDescriptor() {
		super();
		
	}
	
	public EmployeeSearchInputDescriptor(int pageNo, int pageSize) {
		super(pageNo, pageSize);
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
}
