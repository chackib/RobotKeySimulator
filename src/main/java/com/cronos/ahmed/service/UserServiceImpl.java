package com.cronos.ahmed.service;

import com.cronos.ahmed.dto.UserDto;
import com.cronos.ahmed.entities.User;
import com.cronos.ahmed.exception.UserNotFoundException;
import com.cronos.ahmed.mapper.UserMapper;
import com.cronos.ahmed.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(
                ()-> new UserNotFoundException("User not found with ID: " + id) );
    }

    @Override
    public User createUser(UserDto userDto) {
        User user = UserMapper.fromDtoToUser(userDto);
        return userRepository.save(user);
    }

    @Override
        public User updateUser(Long id, UserDto userDto) throws UserNotFoundException {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
                User existingUser = userOptional.get();
                existingUser.setName(userDto.getName());
                existingUser.setSurname(userDto.getSurname());
                existingUser.setBirthdate(userDto.getBirthdate());
                return userRepository.save(existingUser);
            } else {
                throw new UserNotFoundException("User not found with ID: " + id);
            }
        }
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}