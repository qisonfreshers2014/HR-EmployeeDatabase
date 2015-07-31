package com.hred.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hred.model.output.IdValue;
import com.hred.persistence.annotations.Increment;


@Entity
@Table(name = "file")
@Increment
public class File extends AbstractObject {


	@Column(name = "name")
	private String name;
	

	@Column(name = "file_Path")
	private String filePath; // where the actual file is being stored /bucket1/Folder1/key1
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}



    public File() {
    }
	
	@Override
	public int getObjectType() {
		return ObjectTypes.FILE;
	}
    
    public IdValue getIdValue(){
        if(getId()==0)
            return null;
        return new IdValue(getId(),getName());
    }
    

}

