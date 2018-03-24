package com.wran.Controller;

import com.wran.Model.Post;
import com.wran.Model.PostDto;
import com.wran.Model.User;
import com.wran.Repository.UserRepository;
import com.wran.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    PostService postService;

    @GetMapping("/")
    public String showHomePage(Model model){
        List<Post> posts = postService.getPostsInRange(0,10);
        List<PostDto> postDtoList = postService.convertPostListToDto(posts);

        model.addAttribute("posts", postDtoList);
        return "index";
    }
    @GetMapping("/page/{page}")
    public String showPage(@PathVariable("page") int page, Model model){
        List<Post> posts = postService.getPostsInRange((page-1)*10 ,10);
        List<PostDto> postDtoList = postService.convertPostListToDto(posts);

        model.addAttribute("posts", postDtoList);
        return "index";
    }


    @GetMapping("/login")
    public String showLoginPage(@AuthenticationPrincipal User user){
        if(user != null)
            return "redirect:/";

        return "login";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(){
        return "redirect:/";
    }

}
