package com.wran.Service;

import com.wran.Model.User;
import com.wran.Model.UserDto;
import com.wran.Model.Security.UserRole;
import com.wran.Repository.RoleRepository;
import com.wran.Repository.UserRepository;
import com.wran.Validator.UserExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public User registerNewUser(final UserDto newUser) throws UserExistsException {
        if(usernameExists(newUser.getUsername())){
            throw new UserExistsException("There is already an account with that username: " +
                    newUser.getUsername());
        }
        else if(emailExists(newUser.getEmail())){
            throw new UserExistsException("There is already an account with that email address: " +
                    newUser.getEmail());
        }
        else{
            User user = new User(newUser.getUsername(),
                    newUser.getEmail(),
                    passwordEncoder.encode(newUser.getPassword()));
            user.getUserRoles().add(new UserRole(user, roleRepository.findByRoleName("ROLE_USER")));
            userRepository.save(user);
            return user;

        }

    }
    private boolean emailExists(String email){
        return userRepository.findByEmail(email) != null;
    }
    private boolean usernameExists(String username){
        return userRepository.findByUsername(username) != null;
    }
}
