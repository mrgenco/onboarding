package com.mrg.onboarding.document.write;


import com.mrg.onboarding.document.Document;
import com.mrg.onboarding.document.DocumentRepository;
import com.mrg.onboarding.document.DocumentStatus;
import com.mrg.onboarding.document.FileUtils;
import com.mrg.onboarding.document.dto.DocumentRawDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.swing.event.DocumentEvent;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentWriteService {

    private final ModelMapper mapper;
    private final DocumentRepository documentRepository;


    @Transactional
    public void saveDocument(DocumentWriteRequest documentWriteRequest){

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
            } else{
                // CREATE NEW DOCUMENT
                Document document = new Document();
                document.setTitle(documentWriteRequest.getTitle());
                document.setStatus(DocumentStatus.PUBLISHED.getCode());
                document.setSummary(documentWriteRequest.getContent().substring(0,100));
                documentRepository.save(document);
            }

        }

        // File System operations
        // TODO Check if a file with a draft status already exist
        //      if file exist, delete and create new file_draft.md
        //      else create new file.md in the file system


    }

}
