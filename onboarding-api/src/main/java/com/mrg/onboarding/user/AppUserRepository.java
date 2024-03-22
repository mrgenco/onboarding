package com.mrg.onboarding.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

interface AppUserRepository extends CrudRepository<AppUser,Long> {

    Optional<AppUser> findByUsername(String username);

}
