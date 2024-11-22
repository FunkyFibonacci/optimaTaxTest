package com.example.testopttax.service;

import com.example.testopttax.model.User;
import com.example.testopttax.record.UserDto;
import com.example.testopttax.record.UserResponceDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserResponceDto createUser(UserDto userDto);

    List<UserResponceDto> getAllUsers();
    User getUserBySecurityHolder(Authentication authentication);
    UserResponceDto mapModelToDto(User user);
}
