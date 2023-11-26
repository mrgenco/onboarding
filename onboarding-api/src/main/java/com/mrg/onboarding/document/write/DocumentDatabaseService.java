package com.mrg.onboarding.document.write;


import com.mrg.onboarding.document.Document;
import com.mrg.onboarding.document.DocumentRepository;
import com.mrg.onboarding.document.DocumentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentDatabaseService implements DocumentWriteService{

    private final DocumentRepository documentRepository;

    @Transactional
    public void saveDocument(DocumentWriteRequest documentWriteRequest) {

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
                }
                return;
            }
            // CREATE NEW DOCUMENT
            Document document = new Document();
            document.setTitle(documentWriteRequest.getTitle());
            document.setStatus(DocumentStatus.PUBLISHED.getCode());
            document.setSummary(documentWriteRequest.getContent().substring(0,100));
            documentRepository.save(document);
            return;
        }
        throw new IllegalArgumentException("Document can not be published without a valid DocumentStatus!");
    }
}
