package com.example.userservicejan24.service;

import com.example.userservicejan24.dtos.LogoutRequestDto;
import com.example.userservicejan24.exception.TokenNotFoundException;
import com.example.userservicejan24.models.Token;
import com.example.userservicejan24.models.User;
import com.example.userservicejan24.repo.TokenRepository;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;


@Service
public class TokenService {

    private TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;

    }
    public Token generateTokenForUser(User user) {

        Token token = new Token();

        // Generate a unique token value

        token.setValue(RandomStringUtils.randomAlphanumeric(128));

        // Set the user
        token.setUser(user);

        // Set the expiration date to 24 hours from now
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        Date expirationDate = cal.getTime();
        token.setExpirationDate(expirationDate);

        tokenRepository.save(token);

        return token;
    }

    public void deleteToken(String token) {

        Optional<Token> token1 = tokenRepository.findByValueAndDeletedEquals(token, false);

        if (token1.isEmpty()) {
             throw new TokenNotFoundException("Token not found");
        }

        Token t = token1.get();
        t.setDeleted(true);
        tokenRepository.save(t);
    }

    public User validateToken(String token) {

            Optional<Token> token1 = tokenRepository.findByValueAndDeletedEquals(token, false);

            if (token1.isEmpty()) {
                throw new TokenNotFoundException("Token not found");
            }

            Token t = token1.get();

            if (t.getExpirationDate().before(new Date())) {
                throw new TokenNotFoundException("Token expired");
            }

            return t.getUser();
    }
}
