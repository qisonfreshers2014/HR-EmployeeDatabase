package com.hred.model;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hred.persistence.annotations.Increment;

/**
 * @author Bhargavi Uppoju
 *
 */

@Entity
@Table(name="HR_POLICIES")
@Increment
public class HRPolicy extends AbstractObject{

	private long fileId;


	private String policyName;
	
	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	@Override
	public int getObjectType() {
		// TODO Auto-generated method stub
		return ObjectTypes.HR_POLICIES;
	}	
}