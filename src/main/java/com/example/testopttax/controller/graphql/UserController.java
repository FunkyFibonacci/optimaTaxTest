package com.example.testopttax.controller.graphql;

import com.example.testopttax.record.UserResponceDto;
import com.example.testopttax.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("graphqlUserController")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @QueryMapping
    public List<UserResponceDto> getAllUsers() {
        return userService.getAllUsers();
    }
}
