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

        // DB operations
        // TODO : use model mapper for dto2model and base model for last updatedtime and other common fields
        if(documentWriteRequest.getUuid() != null){
            Optional<Document> existingDocument = documentRepository.findByUuid(documentWriteRequest.getUuid());
            Document document;
            if(existingDocument.isPresent()){
                document = existingDocument.get();
                mapper.map(documentWriteRequest, document);
                // todo do this on pre-persist
                document.setSummary(documentWriteRequest.getContent().substring(0,300));
            }else{
                document = new Document();
                document.setTitle(documentWriteRequest.getTitle());
                document.setStatus(DocumentStatus.PUBLISHED.getCode());
                document.setSummary(documentWriteRequest.getContent().substring(0,300));
                // todo do this on pre-persist
                document.setUuid(UUID.randomUUID());
            }
            documentRepository.save(document);
        }



        // File operations
        // Check if a file with a draft status already exist
        // if file exist, delete and create new file_draft.md
        // else create new file.md in the file system


    }

}
