package com.wran.Controller;

import com.wran.Model.PageNavigator;
import com.wran.Model.Post;
import com.wran.Model.PostDto;
import com.wran.Model.User;
import com.wran.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    PostService postService;

    @GetMapping("/")
    public String showHomePage(Model model){
        List<Post> posts = postService.getAcceptedPostsInRange(0,10);
        List<PostDto> postDtoList = postService.convertPostListToDto(posts);

        List<PageNavigator> pnList = postService.getPageList(1);

        model.addAttribute("pnList", pnList);
        model.addAttribute("posts", postDtoList);
        return "index";
    }
    @GetMapping("/page/{page}")
    public String showNextPage(@PathVariable("page") int page, Model model){
        if(page < 1)
            return "redirect:/";

        List<Post> posts = postService.getAcceptedPostsInRange((page-1)*10 ,10);
        List<PostDto> postDtoList = postService.convertPostListToDto(posts);
        List<PageNavigator> pnList = postService.getPageList(page);

        model.addAttribute("pnList", pnList);
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

    @GetMapping("/new")
    public String showNewPage(Model model){
        List<Post> posts = postService.getNotAcceptedPostsInRange(0,10);
        List<PostDto> postDtoList = postService.convertPostListToDto(posts);
        List<PageNavigator> pnList = postService.getPageList(1);

        model.addAttribute("pnList", pnList);
        model.addAttribute("posts", postDtoList);
        return "index";
    }

    @GetMapping("/new/{page}")
    public String showNewNextPage(@PathVariable("page") int page, Model model){
        if(page < 1)
            return "redirect:/new/1";
        List<Post> posts = postService.getNotAcceptedPostsInRange((page-1)*10 ,10);
        List<PostDto> postDtoList = postService.convertPostListToDto(posts);
        List<PageNavigator> pnList = postService.getPageList(page);

        model.addAttribute("pnList", pnList);
        model.addAttribute("posts", postDtoList);
        return "index";
    }
}
