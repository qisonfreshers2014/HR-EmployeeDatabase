/**
 * 
 */
package com.hred.service.descriptors.Input;


/**
 * @author saisudha
 *
 */
public class EmployeeSearchInputDescriptor {

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
	
	
	
}
