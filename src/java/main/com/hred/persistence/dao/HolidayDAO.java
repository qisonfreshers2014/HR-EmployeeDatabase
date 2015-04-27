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
	
	public Holiday getHolidaysById(int id) throws HolidaysException;
	
	public List<Holiday> getHolidays(Holiday holiday);
	
	public Holiday editHoliday(Holiday editedholiday);
	
}
