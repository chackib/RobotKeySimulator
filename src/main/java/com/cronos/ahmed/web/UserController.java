package com.cronos.ahmed.web;

import com.cronos.ahmed.dto.UserDto;
import com.cronos.ahmed.entities.User;
import com.cronos.ahmed.exception.UserNotFoundException;
import com.cronos.ahmed.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @Operation(summary = "Get Users", description = "Get Users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<User> getAllUsers() {

        logger.info("Get All Users request");
        return userService.getAllUsers();
    }

    @Operation(summary = "Get User by ID", description = "User must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) throws UserNotFoundException {

        logger.info("Get User request with id : {}", id);
        return userService.getUserById(id);
    }

    @Operation(summary = "Create User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public User createUser(@RequestBody UserDto userDto) {

        logger.info("Create new User request");
        return userService.createUser(userDto);
    }

    @Operation(summary = "Update User by ID", description = "User must exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDto userDto) throws UserNotFoundException {

        logger.info("Update User request with id : {}", id);
        return userService.updateUser(id, userDto);
    }

    @Operation(summary = "Delete User", description = "Delete User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {

        logger.info("Delete User request with id : {}", id);
        userService.deleteUser(id);
    }
}