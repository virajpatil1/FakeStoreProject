package com.springbootoauthserver.springbootoauthserver.service;


import com.springbootoauthserver.springbootoauthserver.models.SecurityUser;
import com.springbootoauthserver.springbootoauthserver.repo.UserRepository;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public SecurityUser getUserById(Integer id) {

        return userRepository.findById(id).orElse(null);
    }

    public SecurityUser getUserByUsername(String username) {

        return userRepository.findByUsername(username);
    }
}
