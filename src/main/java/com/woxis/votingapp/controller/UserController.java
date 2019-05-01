package com.woxis.votingapp.controller;

import com.woxis.votingapp.dto.UserDTO;
import com.woxis.votingapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<Long> signup(@RequestBody UserDTO userDTO) {
        log.info("Creating user {}", userDTO);
        return ok(userService.createUser(userDTO));
    }
}
