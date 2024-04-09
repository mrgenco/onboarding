package com.mrg.onboarding.document.read;


import com.mrg.onboarding.document.dto.DocumentDto;
import com.mrg.onboarding.document.model.Document;
import com.mrg.onboarding.document.DocumentRepository;
import com.mrg.onboarding.user.AppUser;
import com.mrg.onboarding.user.AppUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentReadService {

    private final DocumentRepository documentRepository;
    private final AppUserService userService;
    private final ModelMapper modelMapper;


    public Optional<Document> getDocumentByUuid(UUID uuid){
        return documentRepository.findByUuid(uuid);
    }


    public List<DocumentDto> getAll() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<AppUser> appUser = userService.findByUsername(username);
        if (appUser.isPresent()) {
            return documentRepository.findByCreatedBy(appUser.get())
                    .stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private DocumentDto convertToDto(Document document) {
        DocumentDto documentDto = modelMapper.map(document, DocumentDto.class);
        String createdByFullName = document.getCreatedBy().getName() + " " + document.getCreatedBy().getSurname();
        String lastUpdatedByFullName = document.getLastUpdatedBy().getName() + " " + document.getLastUpdatedBy().getSurname();
        documentDto.setCreatedBy(createdByFullName);
        documentDto.setLastUpdatedBy(lastUpdatedByFullName);
        return documentDto;
    }
}
