package com.springbootoauthserver.springbootoauthserver.repo;

import com.springbootoauthserver.springbootoauthserver.models.AuthorizationConsent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorizationConsentRepository extends JpaRepository<AuthorizationConsent, AuthorizationConsent.AuthorizationConsentId> {

    Optional<AuthorizationConsent> findByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);

   // Optional<AuthorizationConsent> findByRegisteredClientIdAndPrincipalName(String registeredClientId);
    void deleteByRegisteredClientIdAndPrincipalName(String registeredClientId, String principalName);

}