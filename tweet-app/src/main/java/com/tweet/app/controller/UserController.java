package com.tweet.app.controller;

import com.tweet.app.dto.requestDTO.LoginDTO;
import com.tweet.app.dto.requestDTO.PasswordDTO;
import com.tweet.app.dto.requestDTO.UserRequestDTO;
import com.tweet.app.dto.responseDTO.LoginResponseDTO;
import com.tweet.app.dto.responseDTO.UserResponseDTO;
import com.tweet.app.entity.Users;
import com.tweet.app.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/login")
    LoginResponseDTO getUserByCredentials(@RequestBody LoginDTO loginDTO) throws Exception {
        return service.getUserByCredentials(loginDTO);
    }

    @PutMapping("/{username}/forgot")
    String updateUserForgottenPassword(@PathVariable("username") String userName, @RequestBody PasswordDTO passwordDTO) {
        if(service.validateUserByUserName(userName)) {
        service.updateUserPassword(userName,passwordDTO);
        return "Updated Successfuly";
        }
        return "Failed";
    }

    @GetMapping("/all")
    List<UserResponseDTO> getAllUsers() {
        List<Users> users = service.getAllUsers();
        if(users.size()==0) return List.of();
        return users
                .stream()
                .map(user -> modelMapper.map(user,UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/search/{username}")
    List<UserResponseDTO> getAllUsersByMatchingUserName(@PathVariable("username") String userName) {
        List<Users> users = service.getAllUsersByMatchingUserName(userName);
        if(users.size()==0) return List.of();
        return users
                .stream()
                .map(user -> modelMapper.map(user,UserResponseDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/register")
    UserResponseDTO addNewUser(@RequestBody UserRequestDTO userRequestDTO) {
        return service.addNewUser(userRequestDTO);
    }

    @GetMapping("/{username}")
    UserResponseDTO getUserByUserName(@PathVariable String username) throws Exception {
        if(service.validateUserByUserName(username))
            return modelMapper.map(service.getUserByUserName(username),UserResponseDTO.class);

        return new UserResponseDTO();
    }
}
