package com.agribook.user_service.service;

import com.agribook.user_service.entity.User;
import com.agribook.user_service.model.UserDto;
import com.agribook.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(UserDto userDto) {
        User user = new User(userDto.getUsername(), bCryptPasswordEncoder.encode(userDto.getPassword()),
                userDto.getFirstname(), userDto.getLastname(), userDto.getEmail());
        return userRepository.save(user);
    }

    @Override
    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream().map((user -> new UserDto(user.getUsername(),
                user.getFirstname(), user.getLastname(), user.getEmail()))).toList();
    }
}
