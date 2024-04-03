package com.springbootoauthserver.springbootoauthserver.exceptions;

public class UserNotFoundException extends RuntimeException{

        public UserNotFoundException(String message) {
            super(message);
        }
}
