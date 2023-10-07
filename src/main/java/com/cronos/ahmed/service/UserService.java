package com.cronos.ahmed.service;

import com.cronos.ahmed.dto.UserDto;
import com.cronos.ahmed.entities.User;
import com.cronos.ahmed.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id) throws UserNotFoundException;
    User createUser(UserDto userDto);
    User updateUser(Long id, UserDto userDto) throws UserNotFoundException;
    void deleteUser(Long id);
}