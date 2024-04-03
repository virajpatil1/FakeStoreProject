package com.example.userservicejan24.controllers;


import com.example.userservicejan24.dtos.LoginRequestDto;
import com.example.userservicejan24.dtos.LogoutRequestDto;
import com.example.userservicejan24.dtos.SignupRestquestDto;
import com.example.userservicejan24.exception.UserNotFoundException;
import com.example.userservicejan24.models.Token;
import com.example.userservicejan24.models.User;
import com.example.userservicejan24.service.RoleService;
import com.example.userservicejan24.service.TokenService;
import com.example.userservicejan24.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    private TokenService tokenService;
//
//    @Autowired
//    private WebClient webClient;

    public UserController(@Qualifier("userService") UserService userService, RoleService roleService , TokenService tokenService) {
        this.userService = userService;
        this.roleService = roleService;
        this.tokenService = tokenService;
    }




    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto loginRequestDto) {


        User user = userService.authenticateUser(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        if (user == null) {
            // Authentication failed
            throw new UserNotFoundException("User not found");
        }

        // Generate a token for the user
        Token token = tokenService.generateTokenForUser(user);

        return token;
    };

    @PostMapping("/signup")
    public User signup(@RequestBody SignupRestquestDto signupRestquestDto) {

//        // Create a new user
//        User user = new User();
//        user.setName(signupRestquestDto.getName());
//        user.setEmail(signupRestquestDto.getEmail());
//        user.setHashedPassword(signupRestquestDto.getPassword());
//
//        // Assign roles to the user
//        List<Role> roles = roleService.findRolesByIds(signupRestquestDto.getRoleIds());
//        if (roles.isEmpty()) {
//            // If no roles are provided, assign a default role
//            roles.add(roleService.getDefaultRole());
//        }
//        user.setRoles(roles);
//        user.setEmailVerified(false);

        // Save the user to the database
        return userService.signup(signupRestquestDto);

    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto token) {

        tokenService.deleteToken(token.getToken());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate/{token}")
    public User validateToken(@PathVariable("token") @NonNull String token) {
        return tokenService.validateToken(token);

    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }


//    @GetMapping
//    public String [] getAllUsers(@RegisteredOAuth2AuthorizedClient("abbc70f1-fb59-4b42-b1e4-c52fa0080bea") OAuth2AuthorizedClient client)
//
//    {
//            return this.webClient
//                    .get()
//                    .uri("http://127.0.0.1:9090/")
//                    .attributes(oauth2AuthorizedClient(client))
//                    .retrieve()
//                    .bodyToMono(String[].class)
//                    .block();
//    }

}
