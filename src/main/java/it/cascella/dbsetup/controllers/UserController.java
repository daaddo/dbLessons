package it.cascella.dbsetup.controllers;


import it.cascella.dbsetup.dto.UserDto;

import it.cascella.dbsetup.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")

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

    @Cacheable("users")
    @GetMapping("")
    public List<UserDto> getUsers() throws InterruptedException {
        Thread.sleep(10000);
        return userService.getUsers();
    }



}
