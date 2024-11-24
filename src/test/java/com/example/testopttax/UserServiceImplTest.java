package com.example.testopttax;

import com.example.testopttax.enums.Roles;
import com.example.testopttax.model.Role;
import com.example.testopttax.model.User;
import com.example.testopttax.record.UserDto;
import com.example.testopttax.record.UserResponceDto;
import com.example.testopttax.repo.RoleRepository;
import com.example.testopttax.repo.UserRepository;
import com.example.testopttax.service.UserService;
import com.example.testopttax.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void createUserSuccessCreatedUser(){
        UserDto userDto = new UserDto("John", "Doe", "john.doe", "password123","USER");
        Role mockRole = new Role(1, null, null, Roles.USER.getValue());
        User user = User.builder()
                .firstname("John")
                .lastname("Doe")
                .username("john.doe")
                .password("encodedPassword")
                .role(mockRole)
                .build();

        when(userRepository.getUserByUsername(userDto.username())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(userDto.password())).thenReturn("encodedPassword");
        when(roleRepository.findByRoleIgnoreCase(Roles.USER.getValue())).thenReturn(Optional.of(mockRole));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponceDto result = userService.createUser(userDto);
        assertNotNull(result);
        assertEquals("John", result.firstname());
        assertEquals("Doe", result.lastname());
        assertEquals("john.doe", result.username());
        verify(userRepository, times(1)).save(any(User.class));

    }
}
