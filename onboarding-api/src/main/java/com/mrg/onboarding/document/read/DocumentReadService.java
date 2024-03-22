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
        if(appUser.isPresent())
            return documentRepository.findByCreatedBy(appUser.get().getId())
                    .stream()
                    .map(document -> modelMapper.map(document, DocumentDto.class))
                    .collect(Collectors.toList());
        return Collections.EMPTY_LIST;
    }

    public Optional<Document> getDocumentByTitle(String title) {
        return documentRepository.findByTitle(title);
    }
}
