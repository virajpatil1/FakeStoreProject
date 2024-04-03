package com.springbootoauthserver.springbootoauthserver.controller;

import com.springbootoauthserver.springbootoauthserver.exceptions.UserNotFoundException;
import com.springbootoauthserver.springbootoauthserver.models.Authorization;
import com.springbootoauthserver.springbootoauthserver.models.SecurityUser;
import com.springbootoauthserver.springbootoauthserver.service.AuthorizationService;
import com.springbootoauthserver.springbootoauthserver.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;
import java.security.Principal;


@RestController
@RequestMapping("/users")
public class UserController {

    private static final String CLIENT_ID = "reg-client";
    private UserService userService;

    //private ClientService clientService;

    private AuthorizationService authorizationService;

    public UserController(UserService userService, AuthorizationService authorizationService) {
        this.userService = userService;

        this.authorizationService = authorizationService;

    }

    @GetMapping("/{id}")
    public String getProductId(@PathVariable("id") Long id , Principal principal){

        var restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();

        // Retrieve the SecurityUser object from the database
        SecurityUser user = userService.getUserByUsername(principal.getName());
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        Authorization authorization = authorizationService.findFirstByPrincipalName(principal.getName());
        String accessToken = authorization.getAccessTokenValue();
        // Set the access token in the HTTP header
        httpHeaders.set("Authorization", "Bearer " + accessToken);


        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response =
                restTemplate.exchange("http://localhost:8181/products/id", HttpMethod.GET, entity, String.class, id);

        return response.getBody();

    }


}
