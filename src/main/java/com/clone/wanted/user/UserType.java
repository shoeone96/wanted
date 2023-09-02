package com.clone.wanted.user;

import com.clone.wanted.application.ApplicationStatus;

public enum UserType {
	GENERAL, CORPORATE;

	public static UserType returnStatus(String type){
		if(type.equals("GENERAL")) return UserType.GENERAL;
		return UserType.CORPORATE;
	}
}
