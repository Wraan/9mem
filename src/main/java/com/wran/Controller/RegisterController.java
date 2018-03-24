package com.wran.Controller;

import com.wran.Model.User;
import com.wran.Model.UserDto;
import com.wran.Service.UserService;
import com.wran.Validator.UserExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String showRegistration(Model model, @AuthenticationPrincipal User user){
        if(user != null)
            return "redirect:/";
        model.addAttribute("user", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid UserDto user, BindingResult result, Model model){
        if(!result.hasErrors()){
            try {
                userService.registerNewUser(user);
            }catch(UserExistsException e){
                model.addAttribute("user", new UserDto());
                model.addAttribute("userError", e.getMessage());
                return "/register";
            }
            return "redirect:/login";
        }
        else{
            model.addAttribute("userError", "Error occurred, please try again");
            return "redirect:/register";
        }

    }
}
