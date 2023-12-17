package com.mrg.onboarding.document.write;


import com.mrg.onboarding.document.write.service.DocumentDatabaseService;
import com.mrg.onboarding.document.write.service.DocumentFileSystemService;
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
        // TODO : depending on the login method the documents will be stored on github or CDN

        documentFileSystemService.saveDocument(documentWriteRequest);
    }
}
