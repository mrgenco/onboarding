package com.mrg.onboarding.document.write.service;


import com.mrg.onboarding.document.model.Document;
import com.mrg.onboarding.document.DocumentRepository;
import com.mrg.onboarding.document.DocumentStatus;
import com.mrg.onboarding.document.write.request.DocumentWriteRequest;
import com.mrg.onboarding.document.write.exceptions.NoDocumentFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentWriteDatabaseService{

    private final DocumentRepository documentRepository;

    @Transactional
    public UUID saveDocument(DocumentWriteRequest documentWriteRequest) throws NoDocumentFoundException {

        // Database operations
        Optional<DocumentStatus> requestDocumentStatus = DocumentStatus.findByValue(documentWriteRequest.getStatus());
        if(requestDocumentStatus.isPresent()){
            if(documentWriteRequest.getUuid() != null){
                // UPDATE DOCUMENT
                Optional<Document> existingDocument = documentRepository.findByUuid(documentWriteRequest.getUuid());
                if(existingDocument.isPresent()){
                    Document document = existingDocument.get();
                    document.setStatus(requestDocumentStatus.get().getCode());
                    document.setTitle(documentWriteRequest.getTitle());
                    document.setSummary(documentWriteRequest.getContent().substring(0,100));
                    documentRepository.save(document);
                    return document.getUuid();
                }
                throw new NoDocumentFoundException("No document found with uuid : " + documentWriteRequest.getUuid());
            }
            // CREATE NEW DOCUMENT
            Document document = new Document();
            document.setUuid(UUID.randomUUID());
            document.setTitle(documentWriteRequest.getTitle());
            document.setStatus(DocumentStatus.PUBLISHED.getCode());
            document.setSummary(documentWriteRequest.getContent().substring(0,100));
            documentRepository.save(document);
            return document.getUuid();
        }
        throw new IllegalArgumentException("Document can not be published without a valid DocumentStatus!");
    }
}
