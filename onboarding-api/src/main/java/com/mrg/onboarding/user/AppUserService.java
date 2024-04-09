package com.mrg.onboarding.user;


import com.mrg.onboarding.user.web.UserDto;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {


    private final AppUserRepository appUserRepository;
    private final ModelMapper modelMapper;

    public Optional<AppUser> findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

   public void createUser(AppUser user) {
        appUserRepository.save(user);
    }

    public Optional<UserDto> getAuthenticatedUserDto() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<AppUser> appUser = appUserRepository.findByUsername(username);
        if(appUser.isPresent()){
            UserDto userDto = new UserDto();
            modelMapper.map(appUser.get(), userDto);
            return Optional.of(userDto);
        }
        return Optional.empty();
    }

}
