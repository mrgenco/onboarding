package com.mrg.onboarding.document.write;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class DocumentWriteManager {

    private final DocumentWriteDatabaseService documentWriteDatabaseService;
    private final DocumentWriteFileSystemService documentWriteFileSystemService;

    @Transactional
    UUID saveDocument(DocumentWriteRequest documentWriteRequest) throws NoDocumentFoundException, IOException {
        UUID uuid = documentWriteDatabaseService.saveDocument(documentWriteRequest);
        documentWriteFileSystemService.saveDocument(documentWriteRequest, uuid);
        return uuid;
    }
}
