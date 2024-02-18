package com.mrg.onboarding.document.write;


import com.mrg.onboarding.document.write.exceptions.NoDocumentFoundException;
import com.mrg.onboarding.document.write.request.DocumentWriteRequest;
import com.mrg.onboarding.document.write.service.DocumentWriteDatabaseService;
import com.mrg.onboarding.document.write.service.DocumentWriteFileSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentWriteManager {

    private final DocumentWriteDatabaseService documentWriteDatabaseService;
    private final DocumentWriteFileSystemService documentWriteFileSystemService;

    @Transactional
    public UUID saveDocument(DocumentWriteRequest documentWriteRequest) throws NoDocumentFoundException, IOException {
        UUID uuid = documentWriteDatabaseService.saveDocument(documentWriteRequest);
        //TODO : depending on the preference, save file, github or aws s3 bucket
        documentWriteFileSystemService.saveDocument(documentWriteRequest, uuid);
        return uuid;
    }
}
