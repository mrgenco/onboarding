package com.mrg.onboarding.document;

public enum DocumentStatus {

    DRAFT(1),
    PUBLISHED(2),
    DELETED(3);

    private int code;
    DocumentStatus(int code) {
        this.code=code;
    }

    public int getCode(){
        return this.getCode();
    }
}
