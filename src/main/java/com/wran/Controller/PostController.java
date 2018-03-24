package com.wran.Controller;

import com.wran.Model.Post;
import com.wran.Model.PostDto;
import com.wran.Model.User;
import com.wran.Service.PostService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/post/{id}")
    public String showPost(@PathVariable("id") long id, Model model){
        Post post = postService.findById(id);

        if(post == null)
            return "postNotFound";

        PostDto postdto = new PostDto();
        postdto.setTitle(post.getTitle());
        postdto.setBase64image(new String(Base64.encodeBase64(post.getImage())));
        postdto.setAuthor(post.getUser().getUsername());

        model.addAttribute("post", postdto);
        return "post";

    }

    @GetMapping("/post")
    public String redirectFromPost(){
        return "redirect:/";
    }

    @GetMapping("/uploadPost")
    public String showUploadPostPage(Model model){
        model.addAttribute("post", new PostDto());
        return "uploadPost";
    }

    @PostMapping("/uploadPost")
    public String uploadPost(@ModelAttribute("post") PostDto post, Model model, RedirectAttributes ra, @AuthenticationPrincipal User user){
        Post newPost = postService.convertFromDto(post);

        if(newPost == null) {
            model.addAttribute("error", "Error while uploading a post. Please try again");
            return "uploadPost";
        }

        newPost.setUser(user);
        postService.save(newPost);

        return "redirect:/postUploadedSuccessfully";
    }

    @GetMapping("postUploadedSuccessfully")
    public String showUploadedPost(@AuthenticationPrincipal User user){
        Post post = postService.getLatestPostById(user.getId());

        return "redirect:/post/" + post.getId();
    }

    @GetMapping("/test")
    public String test(){
        List<Post> posts = postService.getPostsInRange(2, 5);

        return "index";
    }
}
