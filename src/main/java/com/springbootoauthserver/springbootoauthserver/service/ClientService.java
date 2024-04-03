package com.springbootoauthserver.springbootoauthserver.service;

import com.springbootoauthserver.springbootoauthserver.repo.ClientRepository;

import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ClientRepository   clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


//    public ClientRegistration findByRegistrationId(String clientId) {
//
//        Client registeredClient = clientRepository.findByClientId(clientId).orElse(null);
//
//        if (registeredClient == null) {
//            throw  new ClientNotFoundException("Client not found");
//        }
//
//        return toClientRegistration(registeredClient);
//
//
//    }

//    private ClientRegistration toClientRegistration(Client registeredClient) {
//
//        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registeredClient.getClientId())
//                .clientId(registeredClient.getClientId())
//                .clientSecret(registeredClient.getClientSecret())
//                .clientName(registeredClient.getClientName())
//                .authorizationGrantType(new AuthorizationGrantType(registeredClient.getAuthorizationGrantTypes()))
//                .redirectUri(registeredClient.getRedirectUris());
//
//        // Split the string into an array and add each method to the builder
//        String[] methods = registeredClient.getClientAuthenticationMethods().split(",");
//        for (String method : methods) {
//            builder.clientAuthenticationMethod(new ClientAuthenticationMethod(method.trim()));
//        }
//
//        // Split the string into an array and add each scope to the builder
//        String[] scopes = registeredClient.getScopes().split(",");
//        for (String scope : scopes) {
//            builder.scope(scope.trim());
//        }
//
//        return builder.build();
//    }
}
