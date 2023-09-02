package com.clone.wanted.application;

public enum ApplicationStatus {
    ONGOING, CANCEL, FAIL, PASS;
    public static ApplicationStatus returnStatus(String type){
        if(type.equals("ONGOING")) return ApplicationStatus.ONGOING;
        if(type.equals("CANCEL")) return ApplicationStatus.CANCEL;
        if(type.equals("FAIL")) return ApplicationStatus.FAIL;
        return ApplicationStatus.PASS;
    }
}
