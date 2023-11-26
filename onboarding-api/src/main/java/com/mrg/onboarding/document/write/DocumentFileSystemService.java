package com.mrg.onboarding.document.write;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentFileSystemService implements DocumentWriteService{
    public void saveDocument(DocumentWriteRequest documentWriteRequest) {

        // File System operations
        // TODO Check if a file with a draft status already exist
        //      if file exist, delete and create new file_draft.md
        //      else create new file.md in the file system

    }
}
