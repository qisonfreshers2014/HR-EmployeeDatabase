/**
 * 
 */
package com.hred.persistence.dao;

import com.hred.exception.UserException;
import com.hred.model.User;


/**
 * AnilRam
 * 
 */
public interface UserDAO extends BaseDAO{

	public User getUserByEmail(String email) throws UserException;


	
}
