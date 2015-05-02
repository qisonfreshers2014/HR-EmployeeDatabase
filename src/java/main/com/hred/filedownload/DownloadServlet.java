package com.hred.filedownload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.hred.exception.ObjectNotFoundException;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.FileDAO;



/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String[][] contentTypesArray={{"xml","text/xml"},{"pdf","application/pdf"},{"text","application/pdf"},
			 {"jpg","image/jpeg"},{"jpeg","image/jpeg"},{"txt","text/plain"}};
	/**
	 * Default constructor.
	 */
	public DownloadServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 */

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String file_id = request.getParameter("fileId");
		FileDAO fileDAOimpl = (FileDAO) DAOFactory.getInstance().getFileDAO();
		com.hred.model.File veiwedFile = null;
		try {
			veiwedFile = fileDAOimpl.getFiles(file_id);
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String fileName = veiwedFile.getName();
		String filepath = veiwedFile.getFilePath();
		int found = filepath.lastIndexOf('.') + 1;
		String extension = "";
		int i = filepath.lastIndexOf('.');
		if (i > 0) {
		    extension = filepath.substring(i+1);
		}
		String contentType="";
		for(int j=0;j>contentTypesArray.length;j++)
		{
			if(contentTypesArray[i][0].equalsIgnoreCase(extension))
			{
				contentType=contentTypesArray[i][1];
			}
		}
		
		
		//String fileType = "";

		// response.setContentType(fileType);
		response.setHeader("Content-disposition", "attachment; filename= "
				+ fileName);
		response.setContentType(contentType);
		// This should send the file to browser
		//OutputStream out = response.getOutputStream();
		File file=new File(filepath);
		response.setContentLength((int)file.length());
		ServletOutputStream out= response.getOutputStream();
		byte[] readFileToByteArray = FileUtils.readFileToByteArray(file);
	
		
		out.write(readFileToByteArray);
		
	}

}