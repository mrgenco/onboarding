package com.mrg.onboarding.document.write.service;

import com.mrg.onboarding.document.write.DocumentWriteRequest;
import com.mrg.onboarding.document.write.exceptions.NoDocumentFoundException;

import java.util.UUID;

public interface DocumentWriteService {
    UUID saveDocument(DocumentWriteRequest documentWriteRequest) throws NoDocumentFoundException;
}
