package com.mrg.onboarding.user;


import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {


    private final AppUserRepository appUserRepository;

    public Optional<AppUser> findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    public Long findCurrentAuthenticatedUserId() {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<AppUser> appUser = appUserRepository.findByUsername(currentUsername);
        return appUser.isPresent() ? appUser.get().getId() : null;
    }

    public void createUser(AppUser user) {
        appUserRepository.save(user);
    }
}
