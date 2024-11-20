package com.example.testopttax.service;

import com.example.testopttax.record.UserDto;
import com.example.testopttax.record.UserResponceDto;

public interface UserService {
    UserResponceDto createUser(UserDto userDto);
}
