package com.mrg.onboarding.document.write;


import com.mrg.onboarding.document.Document;
import com.mrg.onboarding.document.read.DocumentReadService;
import com.mrg.onboarding.document.write.exceptions.NoDocumentFoundException;
import com.mrg.onboarding.document.write.service.DocumentWriteDatabaseService;
import com.mrg.onboarding.document.write.service.DocumentWriteFileSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentWriteManager {

    private final DocumentReadService documentReadService;
    private final DocumentWriteDatabaseService documentWriteDatabaseService;
    private final DocumentWriteFileSystemService documentWriteFileSystemService;

    @Transactional
    public void saveDocument(DocumentWriteRequest documentWriteRequest) throws NoDocumentFoundException, IOException {
        UUID uuid = documentWriteDatabaseService.saveDocument(documentWriteRequest);
        documentWriteFileSystemService.saveDocument(documentWriteRequest, uuid);
    }
}
