package com.hred.service.descriptors.input;
/*
 * Rizwan Khan
 * */



public class ChangePassword {
	String oldPassword;
	String newPassword;
	String confirmNewPassword;
	String id;
	
	public ChangePassword()
	{
		
	}
	
	public ChangePassword(String oldPassword, String newPassword,
			String confirmNewPassword, String id) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.confirmNewPassword = confirmNewPassword;
		this.id = id;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	

}
