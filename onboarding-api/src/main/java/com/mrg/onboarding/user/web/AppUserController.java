package com.mrg.onboarding.user.web;


import com.mrg.onboarding.user.AppUser;
import com.mrg.onboarding.user.AppUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService userService;
    private final ModelMapper modelMapper;
    @GetMapping(value = "/info")
    public ResponseEntity<UserDto> getUserInfo(){
        try{
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<AppUser> appUser = userService.findByUsername(username);
            if(appUser.isPresent()){
                UserDto userDto = new UserDto();
                modelMapper.map(appUser.get(), userDto);
                return new ResponseEntity<>(userDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
