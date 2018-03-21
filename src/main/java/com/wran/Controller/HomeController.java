package com.wran.Controller;

import com.wran.Model.User;
import com.wran.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String showHomePage(Model model){
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model){
        return "login";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(Model model, HttpSession session, Principal principal){
        //Dodanie uzytkownika do sesji

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        return "redirect:/";
    }
}
