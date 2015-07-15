package com.hred.handler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.hred.exception.BusinessException;
import com.hred.exception.ObjectNotFoundException;
import com.hred.model.File;
import com.hred.persistence.dao.DAOFactory;

/**
 * Created by IntelliJ IDEA.
 * User: User
 * Date: Mar 23, 2011
 * Time: 2:46:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileHandler extends AbstractHandler {
    private static FileHandler INSTANCE;


    public FileHandler() {
        super();
    }

    public static FileHandler getInstance() {
        if (null == INSTANCE)
            INSTANCE = new FileHandler();
        return INSTANCE;
    }
    
    public File saveFile(File file) {
    	File fileSaved = (File) DAOFactory.getInstance().getFileDAO().saveObject(file);
		return fileSaved;
	}

    public File getFile(long id) throws BusinessException {
        if (id > 0) {
            return DAOFactory.getInstance().getFileDAO().getFile(id);
        }
        return null;
    }

    public Map<Long, File> getFiles(List<Long> fileIds) {
        Map<Long, File> fileMap = new HashMap<Long, File>();
        if (CollectionUtils.isNotEmpty(fileIds)) {
            List<File> files = DAOFactory.getInstance().getFileDAO().getFiles(fileIds);
            if (CollectionUtils.isNotEmpty(files)) {
                for (File eachFile : files) {
                    fileMap.put(eachFile.getId(), eachFile);
                }
            }
        }
        return fileMap;
    }
    
    public Map<Long, File> getFiles(Long... ids){
        Map<Long, File> fileMap = new HashMap<Long, File>();
        if(null!=ids && ids.length>0){
        
        fileMap =getFiles(Arrays.asList(ids));
        }
        return fileMap;
    }
    
    public Map<String, java.io.File> downloadFiles(List<Long> ids) throws BusinessException, IOException {
        Map<String,java.io.File> downloadMap = new HashMap<>();
        Map<Long, File> files = getFiles(ids);
        Collection<File> values = files.values();
        for(File eachFile : values){
            downloadMap.put(eachFile.getName(),new java.io.File(eachFile.getFilePath()));
        }
        return downloadMap;
    } 
    
    
     public File getFiles(int file_id) throws ObjectNotFoundException{
             File veiwFile= new File();           
            	veiwFile =DAOFactory.getInstance().getFileDAO().getFiles(file_id);            
            return veiwFile;
        }

    
    
}
