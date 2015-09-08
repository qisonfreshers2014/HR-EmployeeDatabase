package com.hred.common;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Timer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.hred.common.cache.CacheHandler;
import com.hred.common.cache.CacheManager;
import com.hred.common.cache.CacheRegionType;
import com.hred.model.NotificationTimer;
import com.hred.model.UserSessionToken;

/**
 * Servlet implementation class InitializationServlet
 */
public class InitializationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		init(config, true);
	}
	protected void init(ServletConfig config, boolean initializeAllServices)
			throws ServletException {
		if (config != null) {
			super.init(config);
		}
		if (initializeAllServices) {
	// Initialize all Services one by one
		initializeServices();
		sendAutomatedNotificationMail();
		}
	}

	protected void initializeServices() throws ServletException {
		try {
			CacheHandler.getInstance().initService();
			UserSessionToken userSessionToken = new UserSessionToken();
			userSessionToken.setUserEmail("test@test.com");
			userSessionToken.setUserId(1);
			String sessionToken = "hlhdsfjlhl2134";
			userSessionToken.setUserSessionId(sessionToken);
			CacheManager.getInstance()
					.getCache(CacheRegionType.USER_SESSION_CACHE)
					.put(sessionToken, userSessionToken);
			
			Object value = CacheManager.getInstance()
			.getCache(CacheRegionType.USER_SESSION_CACHE)
			.getValue(sessionToken);		
			copyFileUploadsToServer();
			
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	public void sendAutomatedNotificationMail()
	{
		Calendar sentAutomatedMailFrom = Calendar.getInstance();
		 sentAutomatedMailFrom.set(2015,8,07,11,00, 00);		
		NotificationTimer mailTimer=new NotificationTimer();
		Timer notificationTimer = new Timer();
		notificationTimer.schedule(mailTimer,sentAutomatedMailFrom.getTime(), 24*60*60*1000);
		//notificationTimer.schedule(mailTimer, 0, 24*60*60*1000);	                    
	}
	public void copyFileUploadsToServer() throws IOException
	{
        String workingDir = System.getProperty("user.dir");
		File file1 = new File(workingDir);
		String parentPath = file1.getParent();
		File actualPath = new File(parentPath, "FileUpload");
		
 		
		File deployFolder = new File(workingDir, "deploy");
		File webAppDir = new File(deployFolder, "webapp");
		
		File tempDir = new File(webAppDir, "FileUpload");
 		FileUtils.copyDirectory(actualPath, tempDir);
		
	}
}