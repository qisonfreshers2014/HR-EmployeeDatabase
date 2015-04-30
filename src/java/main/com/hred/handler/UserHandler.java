package com.hred.handler;



import com.hred.common.Constants;
import com.hred.exception.UserException;
import com.hred.handler.annotations.AuthorizeEntity;
import com.hred.model.User;
import com.hred.persistence.dao.DAOFactory;
import com.hred.persistence.dao.UserDAO;


/**
 * @author Shiva
 *
 */

public class UserHandler extends AbstractHandler {

	
	private static UserHandler INSTANCE = null;

	private UserHandler() {
	}

	/**
	 * @return instance of UserHandler
	 */
	public static UserHandler getInstance() {
		if (INSTANCE == null)
			INSTANCE = new UserHandler();
		return INSTANCE;
	}


	public User getUserByEmail(String email) throws UserException {
		User user = null;
		UserDAO userDAOImpl = DAOFactory.getInstance().getUserDAO();
		user = userDAOImpl.getUserByEmail(email);

		return user;
	}
	@AuthorizeEntity(roles = {Constants.HR})
	public User saveAOP(User user){
		User userSaved=(User) DAOFactory.getInstance().getUserDAO().saveObject(user);
		return userSaved;
	}
	
	


}