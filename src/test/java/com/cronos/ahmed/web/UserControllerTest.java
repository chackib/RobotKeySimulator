package com.cronos.ahmed.web;

import com.cronos.ahmed.dto.UserDto;
import com.cronos.ahmed.entities.User;
import com.cronos.ahmed.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
 class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
     void testGetAllUsers() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "Joao", "Carreira", LocalDate.parse("1980-01-01")));
        userList.add(new User(2L, "Ahmed", "Chakib", LocalDate.parse("1985-05-14")));

        when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Joao")))
                .andExpect(jsonPath("$[1].name", is("Ahmed")));
    }

    @Test
     void testGetUserById() throws Exception {
        User user = new User(1L, "Joao", "Carreira", LocalDate.parse("1980-01-01"));

        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Joao")));
    }
    @Test
     void testCreateUser() throws Exception {
        User newUser = new User(null, "Ahmed", "Chakib", LocalDate.parse("1985-05-14"));
        User savedUser = new User(1L, "Ahmed", "Chakib", LocalDate.parse("1985-05-14"));

        when(userService.createUser(any(UserDto.class))).thenReturn(savedUser);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Ahmed\",\"surname\":\"Chakib\",\"birthdate\":\"1985-05-14\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Ahmed")));
    }

    @Test
     void testUpdateUser() throws Exception {
        User updatedUser = new User(1L, "UpdatedName", "Carreira", LocalDate.parse("1980-01-01"));

        when(userService.updateUser(eq(1L), any(UserDto.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"UpdatedName\",\"surname\":\"Carreira\",\"birthdate\":\"1980-01-01\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("UpdatedName")));
    }

    @Test
     void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());
    }
}