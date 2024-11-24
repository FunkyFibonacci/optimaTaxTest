package com.example.testopttax.service.impl;

import com.example.testopttax.enums.Roles;
import com.example.testopttax.exception.CustomException;
import com.example.testopttax.model.Role;
import com.example.testopttax.model.User;
import com.example.testopttax.record.RoleDto;
import com.example.testopttax.record.UserDto;
import com.example.testopttax.record.UserResponceDto;
import com.example.testopttax.repo.RoleRepository;
import com.example.testopttax.repo.UserRepository;
import com.example.testopttax.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserResponceDto createUser(UserDto userDto) {
        if (userRepository.getUserByUsername(userDto.username()).isPresent()){
            throw new IllegalArgumentException("Пользователь с таким логином существует!");
        }

        User user = buildUser(userDto);

        User userSaved = userRepository.save(user);
        log.info("Успешно создан пользователь: {}", userDto.username());
        return new UserResponceDto(user.getId(), user.getCreatedAt(),user.getUpdatedAt(),user.getFirstname(), userDto.lastname(), userDto.username(),mapToRecordRole(userSaved.getRole()));
    }

    @Override
    public List<UserResponceDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponceDto(
                        user.getId(),
                        user.getCreatedAt(),
                        user.getUpdatedAt(),
                        user.getFirstname(),
                        user.getLastname(),
                        user.getUsername(),
                        mapToRecordRole(user.getRole())
                )).collect(Collectors.toList());
    }

    @Override
    public User getUserBySecurityHolder(Authentication authentication) {
        String username = authentication.getName();
        return userRepository.getUserByUsername(username).orElseThrow(() -> new CustomException("Не найден пользователь с таким логином!"));
    }

    @Override
    public UserResponceDto mapModelToDto(User user) {
        return new UserResponceDto(user.getId(), user.getCreatedAt(),user.getUpdatedAt(),user.getFirstname(),user.getLastname(),user.getUsername(),mapToRecordRole(user.getRole()));
    }

    private User buildUser(UserDto userDto) {
        return User.builder()
                .firstname(userDto.firstname())
                .lastname(userDto.lastname())
                .username(userDto.username())
                .password(passwordEncoder.encode(userDto.password()))
                .role(roleRepository.findByRoleIgnoreCase(Roles.USER.getValue()).orElseThrow(() -> {
                    log.error("Роль не найдена для создания!");
                    return new CustomException("Не найдена роль для создания юзера!");
                }))
                .build();
    }

    private RoleDto mapToRecordRole(Role role){
        return new RoleDto(role.getId(), role.getCreatedAt(),role.getUpdatedAt(),role.getRole());
    }
}
