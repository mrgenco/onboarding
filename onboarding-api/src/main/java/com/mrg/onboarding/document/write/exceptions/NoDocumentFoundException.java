package com.mrg.onboarding.document.write.exceptions;

import java.util.UUID;

public class NoDocumentFoundException extends Exception{

    public NoDocumentFoundException(UUID documentUuid){
        super("No document found with uuid : " + documentUuid);
    }
}
