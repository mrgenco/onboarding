package com.mrg.onboarding.document;

import java.util.Optional;

public enum DocumentStatus {

    DRAFT(1),
    PUBLISHED(2),
    DELETED(3);

    private final int code;
    DocumentStatus(int code) {
        this.code=code;
    }

    public int getCode(){
        return this.code;
    }

    public static Optional<DocumentStatus> findByValue(int code){
        for(DocumentStatus status : DocumentStatus.values()){
            if(status.getCode() == code)
                return Optional.of(status);
        }
        return Optional.empty();
    }
}
