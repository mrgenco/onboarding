package com.mrg.onboarding.document.write;

import java.util.UUID;

class NoDocumentFoundException extends Exception{

    NoDocumentFoundException(UUID documentUuid){
        super("No document found with uuid : " + documentUuid);
    }
}
