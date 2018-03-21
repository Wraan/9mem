package com.wran.Service;

import com.wran.Model.User;
import com.wran.Model.UserDto;
import com.wran.Validator.UserExistsException;


public interface UserService {

    User registerNewUser(UserDto user) throws UserExistsException;
}
