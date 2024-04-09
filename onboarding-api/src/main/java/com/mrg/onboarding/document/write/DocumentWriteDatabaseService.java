package com.mrg.onboarding.document.write;


import com.mrg.onboarding.document.model.Document;
import com.mrg.onboarding.document.DocumentRepository;
import com.mrg.onboarding.document.DocumentStatus;
import com.mrg.onboarding.document.write.DocumentWriteRequest;
import com.mrg.onboarding.document.write.NoDocumentFoundException;
import com.mrg.onboarding.user.AppUser;
import com.mrg.onboarding.user.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class DocumentWriteDatabaseService{

    private final DocumentRepository documentRepository;
    private final AppUserService userService;
    @Transactional
    public UUID saveDocument(DocumentWriteRequest documentWriteRequest) throws NoDocumentFoundException {
        LocalDateTime currentTime = LocalDateTime.now();
        Optional<DocumentStatus> requestDocumentStatus = DocumentStatus.findByValue(documentWriteRequest.getStatus());
        if(requestDocumentStatus.isPresent()){
            if(documentWriteRequest.getUuid() != null){
                Optional<Document> existingDocument = documentRepository.findByUuid(documentWriteRequest.getUuid());
                if(documentRepository.findByUuid(documentWriteRequest.getUuid()).isPresent()){
                    return updateDocument(documentWriteRequest, existingDocument, currentTime, requestDocumentStatus);
                }
                throw new NoDocumentFoundException(documentWriteRequest.getUuid());
            }else{
                return createDocument(documentWriteRequest, currentTime);
            }
        }
        throw new IllegalArgumentException("Document can not be published without a valid DocumentStatus!");
    }

    private UUID updateDocument(DocumentWriteRequest documentWriteRequest, Optional<Document> existingDocument, LocalDateTime currentTime, Optional<DocumentStatus> requestDocumentStatus) {
        Document document = existingDocument.get();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        document.setLastUpdatedBy(userService.findByUsername(username).get());
        document.setLastUpdatedTime(currentTime);
        document.setStatus(requestDocumentStatus.get().getCode());
        document.setTitle(documentWriteRequest.getTitle());
        document.setSummary(documentWriteRequest.getMarkdown().substring(0,100));
        documentRepository.save(document);
        return document.getUuid();
    }

    private UUID createDocument(DocumentWriteRequest documentWriteRequest, LocalDateTime currentTime) {
        Document document = new Document();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        document.setCreatedBy(userService.findByUsername(username).get());
        document.setLastUpdatedBy(userService.findByUsername(username).get());
        document.setUuid(UUID.randomUUID());
        document.setCreatedTime(currentTime);
        document.setLastUpdatedTime(currentTime);
        document.setTitle(documentWriteRequest.getTitle());
        document.setStatus(DocumentStatus.PUBLISHED.getCode());
        document.setSummary(documentWriteRequest.getSummary());
        documentRepository.save(document);
        return document.getUuid();
    }
}
