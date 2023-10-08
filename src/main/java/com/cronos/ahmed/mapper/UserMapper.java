package com.cronos.ahmed.mapper;

import com.cronos.ahmed.dto.UserDto;
import com.cronos.ahmed.entities.User;
import org.springframework.beans.BeanUtils;


public class UserMapper {
    private UserMapper(){}
    public static User fromDtoToUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }
}
