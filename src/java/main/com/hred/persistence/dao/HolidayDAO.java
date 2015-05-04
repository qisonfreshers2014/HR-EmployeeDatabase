/**
 * 
 */
package com.hred.persistence.dao;

import java.util.List;

import com.hred.exception.HolidaysException;
import com.hred.model.Holiday;

/**
 * @author saisudha
 *
 */
public interface HolidayDAO extends BaseDAO{
	
	public List<Holiday> getHolidayById(Holiday holiday) throws HolidaysException;
	
	public List<Holiday> getHolidays();

	/*public Holiday getHolidayById(long id) throws HolidaysException;*/
	
	
		
}
