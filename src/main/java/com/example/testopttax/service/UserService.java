package com.example.testopttax.service;

import com.example.testopttax.record.UserDto;
import com.example.testopttax.record.UserResponceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserResponceDto createUser(UserDto userDto);

    List<UserResponceDto> getAllUsers();
}
