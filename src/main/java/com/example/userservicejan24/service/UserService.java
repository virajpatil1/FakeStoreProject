package com.example.userservicejan24.service;


import com.example.userservicejan24.dtos.SignupRestquestDto;
import com.example.userservicejan24.exception.UserNotFoundException;
import com.example.userservicejan24.exception.passwordNotMatchException;
import com.example.userservicejan24.models.Role;
import com.example.userservicejan24.models.User;
import com.example.userservicejan24.repo.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service("userService")
public class UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleService roleService;
    private UserRepository userRepository;


    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, RoleService roleService,UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleService = roleService;
        this.userRepository = userRepository;

    }

    public User signup(SignupRestquestDto signupRestquestDto) {

        // Create a new user
        User user = new User();
        user.setName(signupRestquestDto.getName());
        user.setEmail(signupRestquestDto.getEmail());
        user.setHashedPassword(bCryptPasswordEncoder.encode(signupRestquestDto.getPassword()));

        // Assign roles to the user
        List<Role> roles = roleService.findRolesByIds(signupRestquestDto.getRoleIds());
        if (roles.isEmpty()) {
            // If no roles are provided, assign a default role
            roles.add(roleService.getDefaultRole());
        }
        user.setRoles(roles);
        user.setEmailVerified(false);

        return userRepository.save(user);
    }

    public User authenticateUser(String email, String password) {
        // Find the user by email
        Optional<User> user = userRepository.findByEmail(email);

        // If the user doesn't exist, throw an exception
        if (user.isEmpty() ) {

            throw new UserNotFoundException("User with email " + email + " not found");
        }

        User user1 = user.get();

        // If the password doesn't match, return error
        if (!bCryptPasswordEncoder.matches(password, user1.getHashedPassword())) {

            throw new passwordNotMatchException("Password does not match");
        }

        // If the user exists and the password matches, return the user
        return user1;
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        return user.get();
    }




}
