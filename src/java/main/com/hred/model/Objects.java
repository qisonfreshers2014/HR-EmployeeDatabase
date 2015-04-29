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
            case ObjectTypes.HOLIDAY:
                persistentObjectName = Holiday.class;
                break;
            default:
                break;
        }
        return persistentObjectName;
    }
}