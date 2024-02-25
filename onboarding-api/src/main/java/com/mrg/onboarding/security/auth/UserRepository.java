package com.mrg.onboarding.security.auth;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<AppUser,Long> {

    Optional<AppUser> findByUsername(String username);

}
