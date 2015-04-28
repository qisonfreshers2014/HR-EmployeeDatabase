package com.hred.persistence.dao;

import java.util.List;


import com.hred.model.SendNotificationHistory;

public interface SendNotificationHistoryDAO extends BaseDAO {



	List<SendNotificationHistory> getHistorydata();


}
