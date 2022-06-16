package com.EventManager.helper;

import com.EventManager.dto.UserDto;
import com.EventManager.model.User;
import org.springframework.beans.BeanUtils;

public class DtoHelper {

    public static User convertToUser(UserDto userDto){
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }
}
