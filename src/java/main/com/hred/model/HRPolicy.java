package com.hred.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hred.persistence.annotations.Increment;

@Entity
@Table(name="HR_POLICIES")
@Increment
public class HRPolicy extends AbstractObject{

	@Column(name="policy_name")
	private String policyName;
	
	@Column(name="file_id")
	private String fileId;

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	@Override
	public int getObjectType() {
		// TODO Auto-generated method stub
		return 0;
	}	
}
