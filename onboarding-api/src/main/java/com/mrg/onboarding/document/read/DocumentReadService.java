package com.mrg.onboarding.document.read;


import com.mrg.onboarding.document.Document;
import com.mrg.onboarding.document.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentReadService {

    private final DocumentRepository documentRepository;

    public Optional<Document> getDocumentByUuid(UUID uuid){
        return documentRepository.findByUuid(uuid);
    }


    public List<Document> getAll() {
        return documentRepository.findAll();
    }
}
