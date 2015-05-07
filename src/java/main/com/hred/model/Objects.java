package com.hred.model;

public class Objects {

	private static Objects INSTANCE;

	private Objects() {
	}

	public static Objects getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Objects();
		}
		return INSTANCE;
	}

    public Class getObjectName(int objectType) {
        Class persistentObjectName = null;
        switch (objectType) {
            case ObjectTypes.USER:
                persistentObjectName = User.class;
                break;

            case ObjectTypes.TEMPLATE:
            	persistentObjectName = Template.class;
            	break;
            case ObjectTypes.ALL_HANDS_MEETING:
            	persistentObjectName = AllHandsMeeting.class;
            	break;

            case ObjectTypes.HOLIDAY:
                persistentObjectName = Holiday.class;
                break;
            case ObjectTypes.FILE:
                persistentObjectName = File.class;
                break;
            case ObjectTypes.EMPLOYEE:
                persistentObjectName = Employee.class;
                break;
            case ObjectTypes.NOTIFICATIONHISTORY:
                persistentObjectName = SendNotificationHistory.class;
                break;

            default:
                break;
        }
        return persistentObjectName;
    }
}