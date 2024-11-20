package com.example.testopttax.service.impl;

import com.example.testopttax.enums.Roles;
import com.example.testopttax.model.User;
import com.example.testopttax.record.UserDto;
import com.example.testopttax.record.UserResponceDto;
import com.example.testopttax.repo.RoleRepository;
import com.example.testopttax.repo.UserRepository;
import com.example.testopttax.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserResponceDto createUser(UserDto userDto) {
        if (userRepository.getUserByUsername(userDto.username()).isPresent()) throw new IllegalArgumentException("Пользователь с таким логином существует!");

        User user = buildUser(userDto);

        userRepository.save(user);
        log.info("Успешно создан пользователь: {}", userDto.username());
        return new UserResponceDto(user.getId(), user.getCreatedAt(),user.getUpdatedAt(),user.getFirstname(), userDto.lastname(), userDto.username());
    }

    private User buildUser(UserDto userDto) {
        return User.builder()
                .firstname(userDto.firstname())
                .lastname(userDto.lastname())
                .username(userDto.username())
                .password(userDto.password())
                .role(roleRepository.findByRole(Roles.USER.getValue()).orElseThrow(
                        () -> new IllegalArgumentException("Роль не найдена")))
                .build();
    }

}
