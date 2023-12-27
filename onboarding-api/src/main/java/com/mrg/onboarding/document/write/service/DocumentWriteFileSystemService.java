package com.mrg.onboarding.document.write.service;


import com.mrg.onboarding.document.FileUtils;
import com.mrg.onboarding.document.write.request.DocumentWriteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentWriteFileSystemService{

    @Value("${document.path}")
    private String documentPath;
    public void saveDocument(DocumentWriteRequest documentWriteRequest, UUID uuid) throws IOException {
        String path = documentPath + uuid + "_" + documentWriteRequest.getStatus()+  ".md";
        FileUtils.deleteIfExist(path);
        FileUtils.createFile(path, documentWriteRequest.getContent());
    }
}
