package com.automation.butler.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public void storeUserDetails(@RequestBody UserDTO userDTO) {
        log.info("Storing user information for {}", userDTO.getName());
        service.storeOrUpdate(userDTO);
    }

    public Optional<UserDTO> getUserCreds(String name) {
        return service.retrieveUserDetails(name);
    }

}
