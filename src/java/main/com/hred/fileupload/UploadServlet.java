package com.hred.fileupload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.hred.common.json.JsonUtil;
import com.hred.handler.FileHandler;

/**
 * *
 *
 * @author Shilpa
 *         ToDO: This servlet must extend the Base Servlet, in future it must extend the BaseServlet where Authentication service is defined
 */
@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {
	private static long TEMP_FILES_CLEANUP_INTERVAL = 1000 * 60 * 60 * 24;
  /*  public final static String ENCRYPTED_URL_PARAM_KEY = "p";*/
	private static String file_service_servlet_upload_location = "RECOM_TEMP_STORAGE";
	private static final String folderName = "FileUpload";
    private static final String UPLOAD_DIRECTORY = File.separator+".."+File.separator+".."+File.separator+".."+File.separator+"upload"+File.separator+folderName;
    private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        service(httpServletRequest, httpServletResponse);
    }

    @SuppressWarnings("rawtypes")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* service(httpServletRequest, httpServletResponse);*/
    	// checks if the request actually contains upload file
    	PrintWriter out = response.getWriter();
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Request does not contain upload data");
            writer.flush();
            return;
        }
         
        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(THRESHOLD_SIZE);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
         
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
         
        // constructs the directory path to store upload file
        String workingDir = System.getProperty("user.dir");
		File file1 = new File(workingDir);
		String parentPath = file1.getParent();
		File actualPath = new File(parentPath, "FileUpload");
	    // creates the directory if it does not exist
	    if(!actualPath.exists()) {
	    	actualPath.mkdir();
        }
        try {
            // parses the request's content to extract file data
            List formItems = upload.parseRequest(request);
            Iterator iter = formItems.iterator();
            com.hred.model.File file= new com.hred.model.File();
   

            // iterates over form's fields            
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                // processes only fields that are not form fields
                if (!item.isFormField()) {
                    String fileName = new File(item.getName()).getName();
                    String basename = FilenameUtils.getBaseName(fileName);
                    String extension = FilenameUtils.getExtension(fileName);
                    long timeStamp = new Date().getTime();
                    String fileNameToSave = basename +"-"+ Long.toString(timeStamp)+"."+ extension;
                    String filePath = actualPath + File.separator + fileNameToSave;
                    File storeFile = new File(filePath);
                    
        	 		
        			File deployFolder = new File(workingDir, "deploy");
        			File webAppDir = new File(deployFolder, "webapp");
        			
        			File tempDir = new File(webAppDir, "FileUpload");
        	 		String tempPath = tempDir + File.separator + fileNameToSave;
        	 		File tempStore = new File(tempPath);
        	 		String tmpPath = File.separator +"FileUpload"+File.separator+fileNameToSave;
                    System.out.println(File.separator);
                    file.setName(fileName);
                    file.setFilePath(tmpPath); 
                    // saves the file on disk
                    item.write(storeFile);
                    item.write(tempStore);
                }
            }
            request.setAttribute("message", "Upload has been done successfully!");
            com.hred.model.File savedFile = FileHandler.getInstance().saveFile(file);
//            FileOutput fileOutput = new FileOutput();
//            fileOutput.setFile(savedFile);
            String jsonString = JsonUtil.getJsonBasedOnDescriptor(savedFile, File.class);
    		out.write(jsonString);	
            
        } catch (Exception ex) {
        	ex.printStackTrace();          
        }
             
    }


    private File getTempPath() {
        String tempDir = System.getProperty("java.io.tmpdir") + File.separator + file_service_servlet_upload_location;
        File tempFile = new File(tempDir);
        if (!tempFile.exists()) {
            if (!tempFile.mkdir()) {
                //  LOGGER.error("unable to create upload directory");
                return null;
            }
        }
        return tempFile;
    }
    
    

   public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        new TemporaryUploadsCleanupThread().start();

    }


    private class TemporaryUploadsCleanupThread extends Thread {
        public void run() {
            try {
                Thread.sleep(TEMP_FILES_CLEANUP_INTERVAL);
                File tempFileUploadDir = getTempPath();
                if (tempFileUploadDir != null && tempFileUploadDir.exists() && tempFileUploadDir.isDirectory()) {
                    String arr[] = {"tmp"};
                    Collection<File> collection = (Collection<File>) FileUtils.listFiles(tempFileUploadDir, arr, false);
                    if (null != collection) {
                        for (File eachFile : collection) {
                            eachFile.delete();
                        }
                    }
                }

            } catch (InterruptedException e) {
                //LOGGER.info("Shutting down TemporaryUploadsCleanupThread");
                return;
            } catch (Exception ex) {
                //  LOGGER.info("Exception while cleaning up temporary upload files : " + ex);
            }

        }
    }


}
