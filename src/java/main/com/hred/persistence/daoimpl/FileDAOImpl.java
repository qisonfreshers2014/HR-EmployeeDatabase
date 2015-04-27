package com.hred.persistence.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.hred.exception.BusinessException;
import com.hred.exception.ExceptionCodes;
import com.hred.exception.ExceptionMessages;
import com.hred.exception.ObjectNotFoundException;
import com.hred.model.File;
import com.hred.model.ObjectTypes;
import com.hred.persistence.dao.FileDAO;

public class FileDAOImpl extends BaseDAOImpl implements FileDAO {

	private static FileDAO INSTANCE = null;


	public static FileDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new FileDAOImpl();
		}
		return INSTANCE;
	}
	
	
	public File saveFile(File file) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public File getFile(long fileId) throws BusinessException {
		File file = null;
		int objectType = ObjectTypes.FILE;
		try {
			file = (File) getObjectById(fileId, objectType);
		} catch (ObjectNotFoundException e) {
			throw new ObjectNotFoundException(
					ExceptionCodes.FILE_DOESNOT_EXIST,
					ExceptionMessages.FILE_DOESNOT_EXIST);
		}
		if(file.getDeleted()){
			throw new BusinessException(
					ExceptionCodes.FILE_IS_DELETED,
					ExceptionMessages.FILE_IS_DELETED);
		}
		return file;
}
    @Override
    public List<File> getFiles(List<Long> fileIds){
        Criteria customCriteria = createCustomCriteria(File.class);
        customCriteria.add(Restrictions.in(File.LABEL_ID,fileIds));
        List<File> fileList = customCriteria.list();
        return fileList;
    }


}
