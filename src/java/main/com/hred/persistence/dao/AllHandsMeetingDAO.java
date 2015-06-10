
package com.hred.persistence.dao;

import java.util.List;

import com.hred.exception.AllHandsMeetingException;
import com.hred.model.AllHandsMeeting;
import com.hred.pagination.AllhandsmeetingInput;
import com.hred.pagination.Paginator;

public interface AllHandsMeetingDAO extends BaseDAO{
//public AllHandsMeeting getAllHandsMeetingById(int id) throws AllHandsMeetingException;
	
	public List<AllHandsMeeting> getAllHandsMeeting();

	/*AllHandsMeeting getAllHandsMeetingById(int id)
			throws AllHandsMeetingException;*/

	List<AllHandsMeeting> getAllHandsMeetingById(AllHandsMeeting allhandsmeeting)
			throws AllHandsMeetingException;
	
	public Paginator<AllHandsMeeting> getAllHandsSchedule(
			AllhandsmeetingInput allhands);



	
}
