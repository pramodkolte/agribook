package com.agribook.user_service.controller;

import com.agribook.user_service.entity.User;
import com.agribook.user_service.model.UserDto;
import com.agribook.user_service.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDto> listUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/users/login")
    public String login(@RequestBody UserDto userDto) {
        return "login";
    }

    @PostMapping("/users/register")
    public String register(@RequestBody UserDto userDto) {
        User user = userService.findByUsername(userDto.getUsername());
        if (user != null) {
            return "User exists!";
        }
        userService.save(userDto);
        return "User register success";
    }
}
