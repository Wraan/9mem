package com.wran.Controller;

import com.wran.Model.Post;
import com.wran.Model.PostDto;
import com.wran.Service.PostService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/post/{id}")
    public String showPost(@PathVariable("id") long id, Model model){
        Post post = postService.findById(id);
        PostDto postdto = new PostDto();
        postdto.setTitle(post.getTitle());
        postdto.setBase64image(new String(Base64.encodeBase64(post.getImage())));
        if(post != null){
            model.addAttribute("post", postdto);
            return "post";
        }
        else{
            return "postNotFound";
        }
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
    public String uploadPost(@ModelAttribute("post") PostDto post, Model model, RedirectAttributes ra){
        Post newPost = postService.convertFromDao(post);

        if(newPost == null) {
            model.addAttribute("error", "Error while uploadring a post. Please try again");
            return "uploadPost";
        }

        return "redirect:/postUploadedSuccessfully";
    }

    @GetMapping("postUploadedSuccessfully")
    public String showUploadedPost(Model model){
        //
        return "/post/20";
    }
}
