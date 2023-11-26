package com.mrg.onboarding.document.write;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DocumentWriteManager {

    private final DocumentDatabaseService documentDatabaseService;
    private final DocumentFileSystemService documentFileSystemService;

    @Transactional
    public void saveDocument(DocumentWriteRequest documentWriteRequest) {
        documentDatabaseService.saveDocument(documentWriteRequest);
        documentFileSystemService.saveDocument(documentWriteRequest);
    }
}
