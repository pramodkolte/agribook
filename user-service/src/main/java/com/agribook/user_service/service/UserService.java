package com.agribook.user_service.service;

import com.agribook.user_service.entity.User;
import com.agribook.user_service.model.UserDto;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    User save(UserDto userDto);

    List<UserDto> findAllUsers();
}
