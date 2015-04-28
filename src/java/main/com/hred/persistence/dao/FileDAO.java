package com.hred.persistence.dao;

import java.io.IOException;
import java.util.List;

import com.hred.exception.BusinessException;
import com.hred.exception.ObjectNotFoundException;
import com.hred.model.File;

public interface FileDAO extends BaseDAO {
	public File saveFile(File file) throws IOException;
	public File getFile(long fileId) throws ObjectNotFoundException, BusinessException;

    List<File> getFiles(List<Long> fileIds);
}
