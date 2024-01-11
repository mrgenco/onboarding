package com.mrg.onboarding.document.read;


import com.mrg.onboarding.document.dto.DocumentDto;
import com.mrg.onboarding.document.model.Document;
import com.mrg.onboarding.document.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentReadService {

    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;


    public Optional<Document> getDocumentByUuid(UUID uuid){
        return documentRepository.findByUuid(uuid);
    }


    public List<DocumentDto> getAll() {
        return documentRepository.findAll()
                .stream()
                .map(document -> modelMapper.map(document, DocumentDto.class))
                .collect(Collectors.toList());
    }

    public Optional<Document> getDocumentByTitle(String title) {
        return documentRepository.findByTitle(title);
    }
}
