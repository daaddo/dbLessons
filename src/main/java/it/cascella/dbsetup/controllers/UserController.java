package it.cascella.dbsetup.controllers;


import it.cascella.dbsetup.dto.UserDto;

import it.cascella.dbsetup.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    //utilizziamo la @Valid per validare i dati in input
    public ResponseEntity<String> saveUser(@Valid @RequestBody UserDto user) {
        userService.saveUser(UserDto.fromDtoto(user));
        return ResponseEntity.status(HttpStatus.CREATED).body("User successfully registered");
    }



}
