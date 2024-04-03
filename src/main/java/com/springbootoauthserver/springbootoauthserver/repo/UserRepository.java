package com.springbootoauthserver.springbootoauthserver.repo;

import com.springbootoauthserver.springbootoauthserver.models.SecurityUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SecurityUser, Integer> {

    SecurityUser findByUsername(String username);
}