package com.springbootoauthserver.springbootoauthserver.service;


import com.springbootoauthserver.springbootoauthserver.models.Authorization;
import com.springbootoauthserver.springbootoauthserver.repo.AuthorizationConsentRepository;
import com.springbootoauthserver.springbootoauthserver.repo.AuthorizationRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class AuthorizationService {

    private AuthorizationRepository authorizationRepository;

    public AuthorizationService(AuthorizationRepository authorizationRepository) {
        this.authorizationRepository = authorizationRepository;
    }

    public Authorization findFirstByPrincipalName(String principalName) {
        return authorizationRepository.findFirstByPrincipalNameOrderByAuthorizationCodeIssuedAtDesc(principalName).orElse(null);
    }
}
