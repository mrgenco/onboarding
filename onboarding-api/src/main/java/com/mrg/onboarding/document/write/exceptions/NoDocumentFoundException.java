package com.mrg.onboarding.document.write.exceptions;

import java.util.UUID;

public class NoDocumentFoundException extends Exception{

    public NoDocumentFoundException(String message){
        super(message);
    }
}
