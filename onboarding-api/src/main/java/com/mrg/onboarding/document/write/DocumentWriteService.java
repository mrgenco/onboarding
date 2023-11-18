package com.mrg.onboarding.document.write;


import com.mrg.onboarding.document.Document;
import com.mrg.onboarding.document.DocumentRepository;
import com.mrg.onboarding.document.DocumentStatus;
import com.mrg.onboarding.document.FileUtils;
import com.mrg.onboarding.document.dto.DocumentRawDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.event.DocumentEvent;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentWriteService {

    private final DocumentRepository documentRepository;

    @Transactional
    public void saveAsDraft(DocumentRawDto documentRawDto){

        // DB operations
        // Check if record exist with draft status
        // if record exist, update information
        // else create new record with Draft status

        // File operations
        // Check if a file with a draft status already exist
        // if file exist, delete and create new file_draft.md
        // else create new file.md in the file system


    }

    @Transactional
    public void saveAsPublished(DocumentRawDto documentRawDto){



    }
}