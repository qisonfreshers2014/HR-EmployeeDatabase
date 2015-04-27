package com.hred.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="FILEUPLOAD")
public class FileUpload extends AbstractObject{
	@Column(name = "file_id")
	 private int fileId;
	
	@Column(name = "file_name")
	 private String fileName; 
	
	 @Column(name = "file_path")
	 private String filePath;
	 
	 @Column(name = "file_type")
	 private String fileType;
	
	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

		@Override
	public int getObjectType() {
		// TODO Auto-generated method stub
		return 0;
	}
}
