package com.example.testopttax.controller.rest;

import com.example.testopttax.record.UserDto;
import com.example.testopttax.record.UserResponceDto;
import com.example.testopttax.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponceDto> createUser(@RequestBody UserDto userDto){
        UserResponceDto responceDto = userService.createUser(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responceDto);
    }
}
